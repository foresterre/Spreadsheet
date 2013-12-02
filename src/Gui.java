import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;



public class Gui extends JFrame {
	public int x = 1;
	private JPanel panel;
    private JTextArea area;
    private JTable table;
    private DefaultTableModel DTM;
    private FocusListener focusListener;

    public Gui() {
    	
    	JMenuBar menubar = new JMenuBar();
        
        DTM = new DefaultTableModel(Sheet.getColumns(),Sheet.getRows());
		this.table = new JTable(DTM);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);
		table.changeSelection(0, 0, false, false);
		
    	this.focusListener = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				
				String a1 = arg0.paramString();
				
				int a = table.getSelectedRow();
				int a2 = table.getSelectedColumn();
				System.out.println(a);
				System.out.println(a2);
			}
			

			@Override
			public void focusLost(FocusEvent arg0) {
			}};
			
			this.table.addFocusListener(focusListener);
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();
		
        //menubar 'File'
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        
        //menuitem 'New'
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileNew.setToolTipText("Make an new spreadsheet");
        fileNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {

        		DTM = new DefaultTableModel(Sheet.getColumns(),Sheet.getRows());
        		table = new JTable(DTM);
        		
        		JScrollPane pane = new JScrollPane(table);
        		add(pane);
        		
                setTitle("New spreadsheet " + x);
                x++;
                setSize(new Dimension(1365, 767));
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
            
        	}
        });
        
        
        //menuitem 'Open'
        JMenuItem fileOpen = new JMenuItem("Open");
        fileNew.setMnemonic(KeyEvent.VK_O);
        fileOpen.setToolTipText("Open an spreadsheet");
        fileOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("c files", "c");
                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(panel, "Open file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    String text = readFile(file);
                    area.setText(text);
            }}
        });
        
        //menuitem 'Save'
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setMnemonic(KeyEvent.VK_S);
        fileSave.setToolTipText("Save spreadsheet");
        
        //menuitem 'Save as...'
        JMenuItem fileSaveas = new JMenuItem("Save as...");
        fileSave.setMnemonic(KeyEvent.VK_S);
        fileSave.setToolTipText("Save spreadsheet as...");

        //menuitem 'Exit'
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_W);
        fileExit.setToolTipText("Exit application");
        fileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
 
        //link the menuitems to 'File'
        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        file.add(fileSaveas);
        file.addSeparator();
        file.add(fileExit);

        menubar.add(file);

        setJMenuBar(menubar);

        setTitle("Spreadsheet");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Gui ex = new Gui();
                ex.setVisible(true);
            }
        });
    }
    
    public String readFile(File file) {

        StringBuffer fileBuffer = null;
        String fileString = null;
        String line = null;

        try {
            FileReader in = new FileReader(file);
            BufferedReader brd = new BufferedReader(in);
            fileBuffer = new StringBuffer();

            while ((line = brd.readLine()) != null) {
                fileBuffer.append(line).append(
                        System.getProperty("line.separator"));
            }

            in.close();
            fileString = fileBuffer.toString();
        } catch (IOException e) {
            return null;
        }
        return fileString;
    }
}
