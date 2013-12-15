package sheetproject.gui;
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

import sheetproject.controller.*;
import sheetproject.spreadsheet.*;



public class Gui extends JFrame {
	public int x = 1;
	public int currentlySelectedRow = 0;
	public int currentlySelectedColumn = 0;
	private JPanel panel;
    private JTextArea area;
    private JTable table;
    private DefaultTableModel DTM;
    private FocusListener focusListener;

    public Gui(MainController main)
    {
    	// Create a new menubar
    	JMenuBar menubar = new JMenuBar();
        
    	// Create a new table
        DTM = new DefaultTableModel(Sheet.getColumns(),Sheet.getRows());
		this.table = new JTable(DTM);
		
		JScrollPane pane = new JScrollPane(table);
		add(pane);

    	this.focusListener = new FocusListener()
    	{

			@Override
			public void focusGained(FocusEvent arg0)
			{
				
				String a1 = arg0.paramString();
			
				
				currentlySelectedRow = table.getSelectedRow();
				currentlySelectedColumn = table.getSelectedColumn();
				System.out.println(currentlySelectedRow);
				System.out.println(currentlySelectedColumn);
			
			}
			

			@Override
			public void focusLost(FocusEvent arg0) {
				String valuex = (String) table.getValueAt(currentlySelectedRow, currentlySelectedColumn);
				System.out.println("The value of Cell (" + currentlySelectedRow + ", " + currentlySelectedColumn + ") is " + valuex + ".");
			}};
			
			this.table.addFocusListener(focusListener);
			initUI();
    	}

    public final void initUI()
    {

        JMenuBar menubar = new JMenuBar();
		
        //menubar 'File'
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        
        //menuitem 'New'
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileNew.setToolTipText("Make an new spreadsheet");
        fileNew.addActionListener(new ActionListener() 
        {
	        public void actionPerformed(ActionEvent event) 
	        {
	
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
        fileOpen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
            	JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("c files", "c");
                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(panel, "Open file");

                if (ret == JFileChooser.APPROVE_OPTION)
                {
                	File file = fileopen.getSelectedFile();
                 	String filename = file.getName();
                 	MainController.openFile(filename);
                    
                    for(String key : MainController.getSheet().getCells().keySet())
                    {
                    	String[] index = key.split(",");
                    	int columnIndex = Integer.parseInt(index[0]);
                    	int rowIndex = Integer.parseInt(index[1]);
                    	
                    	Cell cell = MainController.getSheet().getCells().get(key);
                    	String value = cell.getValue();
                    	
                    	table.setValueAt(value, rowIndex, columnIndex); 	
                    }
                }
            }
        });
        
        //menuitem 'Save'
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setMnemonic(KeyEvent.VK_S);
        fileSave.setToolTipText("Save spreadsheet");
        fileSave.addActionListener(new ActionListener()
        {

			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filesave = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("c files", "c");
                filesave.addChoosableFileFilter(filter);

                int ret = filesave.showDialog(panel, "Save file");
                
                if (ret == JFileChooser.APPROVE_OPTION)
                {
                	for(int i = 0; i < Sheet.getRows(); i++)
                	{
                		for(int j = 0; j < Sheet.getColumns(); j++)
                		{
                			String key = i + "," + j;
                			Cell value = (Cell) table.getModel().getValueAt(i, j);
                			
                			MainController.getSheet().getCells().put(key, value);
                		}
                	}
                }
				
			}
        	
        	
        });
        
        //menuitem 'Save as...'
        JMenuItem fileSaveas = new JMenuItem("Save as...");
        fileSave.setMnemonic(KeyEvent.VK_A);
        fileSave.setToolTipText("Save spreadsheet as...");

        //menuitem 'Exit'
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_W);
        fileExit.setToolTipText("Exit application");
        fileExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event) 
            {
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

//    public static void main(String[] args)
//    {
//        SwingUtilities.invokeLater(new Runnable() 
//        {
//            public void run() 
//            {
//                Gui ex = new Gui();
//                ex.setVisible(true);
//            }
//        });
//    }
    
//    public String readFile(File file) 
//    {
//
//        StringBuffer fileBuffer = null;
//        String fileString = null;
//        String line = null;
//
//        try 
//        {
//            FileReader in = new FileReader(file);
//            BufferedReader brd = new BufferedReader(in);
//            fileBuffer = new StringBuffer();
//
//            while ((line = brd.readLine()) != null) 
//            {
//                fileBuffer.append(line).append(
//                        System.getProperty("line.separator"));
//            }
//
//            in.close();
//            fileString = fileBuffer.toString();
//        } 
//        catch (IOException e)
//        {
//            return null;
//        }
//        return fileString;
//    }
}
