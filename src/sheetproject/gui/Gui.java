package sheetproject.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;



public class Gui extends JFrame {
	public int x = 1;
	private JPanel panel;
    private JTextArea area;

    public Gui() {
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileNew.setToolTipText("Make an new spreadsheet");
        fileNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		int y=0;
        		for(int i=0;i<=1;i++){
	        		JPanel panel = new JPanel();
	                panel.setLayout(new BorderLayout());
	                panel.setBorder(BorderFactory.createEmptyBorder(0+y, 0, 690, 1300-y));
	            
	                JTextArea area = new JTextArea();
	
	                area.setLineWrap(true);
	                area.setWrapStyleWord(true);
	                area.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
	
	                panel.add(area);
	
	                add(panel);
	                y=y+70;
	        		}

                setTitle("New spreadsheet " + x);
                x++;
                setSize(new Dimension(1365, 767));
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
            
        	}
        });

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

        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setMnemonic(KeyEvent.VK_S);
        fileSave.setToolTipText("Save spreadsheet");

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_W);
        fileExit.setToolTipText("Exit application");
        fileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });

        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
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
