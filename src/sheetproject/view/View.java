package sheetproject.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.net.URI;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import sheetproject.controller.MainController;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.alfabet.Alfabet;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class View extends JFrame 
{
	
	private MainController controller;
	
	private String applicationTitle = "Scarlett";
	
	private String applicationImageLocation = "applicationIcon-128x128.png";
	
	private JToolBar toolbar;
	
	private JTable table;
	
	private JPanel statusPanel;
	
	private JLabel statusLabel;
	
	JTextField selectionIndicator;
	
	JTextField textField;
	
	private JScrollPane scrollPane;
	
	int newDocument = 0;
	
	boolean openDocument = false;
	
	public View(MainController controller)
	{
		// Style
		JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		
		
		// Set visibility to TRUE
		this.setVisible(true);
		
		// Set default closing action
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new FileExit(this));
	
		// Make application maximized
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		
		// Stores the controller reference
		this.setController(controller);
		
		// Set Title
		this.changeTitle(null);
		
		// Set Application Icon
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image titleIcon = tk.getImage(this.applicationImageLocation);
		this.setIconImage(titleIcon);
		
		this.setupLayout();
		this.getController().newFile();
		this.setupTable();
		this.changeTitle("New Spreadsheet");
		this.newDocument++;
		
	}
	
	public void setupLayout()
	{
		
		JMenuBar menubar = new JMenuBar();
        
        // Menu bar item 'File'
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        
        // Menu item 'New'
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        fileNew.setToolTipText("New File");
        fileNew.addActionListener(new FileNew(this));
        
        
        // Menu item 'Open'
        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.setMnemonic(KeyEvent.VK_O);
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        fileOpen.setToolTipText("Open File");
        fileOpen.addActionListener(new FileOpen(this));
        
        // Menu item 'Save'
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setMnemonic(KeyEvent.VK_S);
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        fileSave.setToolTipText("Save File");
        fileSave.addActionListener(new FileSave(this));
        
        // Menu item 'Save As'
        JMenuItem fileSaveAs = new JMenuItem("Save As");
        fileSaveAs.setMnemonic(KeyEvent.VK_A);
        fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        fileSaveAs.setToolTipText("Save File As");
        fileSaveAs.addActionListener(new FileSaveAs(this));
        
        // Menu item 'Print'
        JMenuItem filePrint = new JMenuItem("Print");
        filePrint.setMnemonic(KeyEvent.VK_P);
        filePrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        filePrint.setToolTipText("Print File");
        filePrint.addActionListener(new FilePrint(this));

        // Menu item 'Exit'
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_Q);
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        fileExit.setToolTipText("Exit Application");
        fileExit.addActionListener(new FileExit(this));
        
        // COMMENTING THESE OUT FOR BETTER PRESENTATION SCREENSHOTS + CLASS WRECKING() BELOW
        // ALSO SET DEBUG TO ZERO (SAME REASON)
        //JMenuItem Wrecking = new JMenuItem("Wreck it");
        //Wrecking.setMnemonic(KeyEvent.VK_W);
        //Wrecking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        //Wrecking.setToolTipText("Wreck it, boy");
        //Wrecking.addActionListener(new Wrecking());
        
        // Add menu items to file menu bar item
        //file.add(Wrecking);
        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        file.add(fileSaveAs);
        file.addSeparator();
        file.add(filePrint);
        file.addSeparator();
        file.add(fileExit);

        menubar.add(file);

        this.setJMenuBar(menubar);
        
        this.toolbar = new JToolBar("Tools");
        this.toolbar.setPreferredSize(new Dimension(this.getWidth(), 24));
        this.toolbar.setFloatable(false);
        this.toolbar.setRollover(true);
        
        selectionIndicator = new JTextField();
    
        selectionIndicator.setEditable(false);
        selectionIndicator.setMaximumSize(new Dimension(103, 20));
        selectionIndicator.setPreferredSize(new Dimension(103, 20));
        selectionIndicator.setBackground(Color.WHITE);
        selectionIndicator.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
        this.toolbar.add(selectionIndicator);
        
        textField = new JTextField();
        this.toolbar.add(textField);
        
        this.add(this.toolbar, BorderLayout.PAGE_START);
        
        this.statusPanel = new JPanel();
        this.add(this.statusPanel, BorderLayout.SOUTH);
        this.statusPanel.setPreferredSize(new Dimension(this.getWidth(), 24));
        this.statusPanel.setLayout(new BoxLayout(this.statusPanel, BoxLayout.X_AXIS));
        this.statusLabel = new JLabel("Status");
        this.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.statusPanel.add(this.statusLabel);
	}
	

	

	public void setupTable()
	{
		
		// Setup default table
		DefaultTableModel dtm = new DefaultTableModel(Sheet.getRows(),Sheet.getColumns());
		
		this.setTable(new JTable(dtm));
		this.getTable().setShowGrid(true);
		this.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTable().setFillsViewportHeight(true);
		this.getTable().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.getTable().setCellSelectionEnabled(true);
		this.getTable().getTableHeader().setReorderingAllowed(false);
		
		this.getTable().addMouseListener(new TextFieldUpdate(this));
//		this.getTable().addMouseListener(new MouseAdapter()
//		{
//            
//            @Override
//            public void mouseClicked(MouseEvent e)
//            {
//            	int selectedColumn = getTable().columnAtPoint(e.getPoint());
//                int selectedRow = getTable().rowAtPoint(e.getPoint());
//                    
//                try 
//                {
//					selectionIndicator.setText(Alfabet.parseInt(selectedColumn + 1) + (selectedRow + 1));
//                } 
//                catch (NumberOutOfBoundsException e1) 
//                {
//				}
//                    
//                //String displayString = (String) getTable().getValueAt(selectedRow, selectedColumn);
//                try 
//                {
//                	textField.setText(getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
//                }
//                catch(NullPointerException e1)
//                {
//                	textField.setText("");
//                }
//                    
//            }
//		});
		
		this.getTable().getModel().addTableModelListener(new TableUpdate(this));

		// Setup row number table
		TableRowSorter<TableModel> rowsorter = new TableRowSorter<TableModel>(getTable().getModel());
		this.getTable().setRowSorter(rowsorter);
		       
		AbstractTableModel model = new AbstractTableModel() 
		{
		 
			static final long serialVersionUID = 1L;
           
            public int getColumnCount() 
            {
            	return getTable().getColumnCount();
            }
 
            public Object getValueAt(int row, int column) 
            {
                return Integer.toString(row + 1);
            }
 
            public int getRowCount() 
            {
                return getTable().getRowCount();
            }
		};
		
		JTable Tablerow = new JTable(model);
		Tablerow.setShowGrid(true);
		Tablerow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tablerow.setPreferredScrollableViewportSize(new Dimension(30, 0));
		Tablerow.getColumnModel().getColumn(0).setPreferredWidth(30);
		Tablerow.setBackground(UIManager.getColor(getTable().getBackground()));
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		Tablerow.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		
		
		 
		// Add both tables to the scroll pane
		this.scrollPane = new JScrollPane(getTable());
		scrollPane.setRowHeaderView(Tablerow);
		
		
        this.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void clearTable()
	{
		MainController controller = this.getController();
		for(String key : controller.getSheet().getCells().keySet())
        {
                String[] index = key.split(",");
                int columnIndex = Integer.parseInt(index[0]);
                int rowIndex = Integer.parseInt(index[1]);
                
                this.getTable().setValueAt("", rowIndex -1, columnIndex -1);         
        }
	}
	
	public void reloadTable()
	{
		this.openDocument = true;
        
        for(String key : this.getController().getSheet().getCells().keySet())
        {
                String[] index = key.split(",");
                int columnIndex = Integer.parseInt(index[0]);
                int rowIndex = Integer.parseInt(index[1]);
                
                Cell cell = this.getController().getSheet().getCells().get(key);
                String value = cell.getValue();
                
                this.getTable().setValueAt(value, rowIndex -1, columnIndex -1);         
        }
        
        this.openDocument = false;
	}
	
	public boolean isTableChanged()
	{
		MainController controller = this.getController();
		for(String key : controller.getSheet().getCells().keySet())
        {              
                Cell cell = controller.getSheet().getCells().get(key);
                if (cell.getState() == Cell.EDITED)
                {
                	return true;
                }
        }
		return false;
	}
	
	public void changeTitle(String title)
	{
		if (title == null)
		{
			if (MainController.DEBUG)
			{
				this.setTitle(this.applicationTitle + " - DEBUGGING MODE" );
			}
			else
			{
				this.setTitle(this.applicationTitle);
			}
		}
		else
		{
			if (MainController.DEBUG)
			{
				this.setTitle(title + " - " + this.applicationTitle + " - DEBUGGING MODE" );
			}
			else
			{
				this.setTitle(title + " - " + this.applicationTitle);
			}
		}
	}
	
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(
				this,
			    message
		);
	}
	
	public void showWarning(String message)
	{
		JOptionPane.showMessageDialog(
				this,
			    message,
			    "Warning",
			    JOptionPane.WARNING_MESSAGE
		);
	}
	
	public void showError(String message)
	{
		JOptionPane.showMessageDialog(
				this,
			    message,
			    "Error",
			    JOptionPane.ERROR_MESSAGE
		);
	}
	
	public void changeStatus(String status)
	{
		this.statusLabel.setText(status);
	}
	
	public String getStatus() {
		return statusLabel.getText();
	}

	public void setStatus(String status) {
		this.statusLabel.setText(status);
	}

	public String getApplicationTitle() {
		return applicationTitle;
	}

	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

	public MainController getController() {
		return controller;
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}

class FileNew implements ActionListener
{
	private View view;
	
	public FileNew(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if(this.view.getController().getFilename() == null)
			{
				if (this.view.newDocument - 1 == 0)
				{
					fileName = "New Spreadsheet";
				}
				else
				{
					fileName = "New Spreadsheet" + (this.view.newDocument - 1);
				}
			}
			else
			{
				fileName = this.view.getController().getFilename().getName();
			}
			
			Object[] options = 
			{
					"Save",
					"Don't Save",
					"Cancel"
            };
			
			int n = JOptionPane.showOptionDialog(
				this.view,
			    "Want to save your changes to " + fileName + "?",
			    this.view.getApplicationTitle(),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]
			 );
			
			if(n == 0)
			{
				new FileSave(this.view).actionPerformed(e);
				this.function();
			}
			else if(n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
			}
		}
	}
	
	public void function()
	{
		this.view.clearTable();
		this.view.getController().newFile();
		
		if (this.view.newDocument == 0)
		{
			this.view.changeTitle("New Spreadsheet");
			this.view.newDocument++;
		}
		else
		{
			this.view.changeTitle("New Spreadsheet" + this.view.newDocument);
			this.view.newDocument++;
		}
	}
}

class FileOpen implements ActionListener
{
	private View view;
	
	public FileOpen(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if(this.view.getController().getFilename() == null)
			{
				if (this.view.newDocument - 1 == 0)
				{
					fileName = "New Spreadsheet";
				}
				else
				{
					fileName = "New Spreadsheet" + (this.view.newDocument - 1);
				}
			}
			else
			{
				fileName = this.view.getController().getFilename().getName();
			}
			
			Object[] options = 
			{
					"Save",
					"Don't Save",
					"Cancel"
            };
			
			int n = JOptionPane.showOptionDialog(
				this.view,
			    "Do you want to save your changes to " + fileName + "?",
			    this.view.getApplicationTitle(),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]
			 );
			
			if(n == 0)
			{
				new FileSave(this.view).actionPerformed(e);
				this.function();
			}
			else if(n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
			}
		}
	}
	
	public void function()
	{		
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.setFileFilter(new OpenFileFilter(".scar"));
		
		int ret = fileOpen.showDialog(view, "Open");
		
		if (ret == JFileChooser.APPROVE_OPTION)
        {
			MainController controller = view.getController();
			
			this.view.clearTable();
			
	        File file = fileOpen.getSelectedFile();
	        controller.openFile(file);
	        
	        String[] fileName = file.getName().split("\\.");
	        String tempFileName = fileName[0];
	        
	        for (int i = 1; i < fileName.length; i++)
	        {
	        	
	        	if (i != fileName.length - 1)
	        	{
	        		tempFileName += fileName[i];
	        	}
	        }
	        
	        this.view.changeTitle(tempFileName);
	        
	        this.view.reloadTable();
        }
	}
}

//class Wrecking implements ActionListener
//{
//	public void actionPerformed(ActionEvent e)
//	{
//		try {
//			 // Create Desktop object
//			 Desktop d=Desktop.getDesktop();
//
//			 // Browse an URL
//			 d.browse(new URI("http://www.youtube.com/watch?v=My2FRPA3Gf8")); 
//			}
//			catch(Exception ex) {
//			       ex.printStackTrace();
//		}
//	}
//}

class FileSave implements ActionListener
{
	private View view;
	
	public FileSave(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(this.view.getController().getFilename() == null)
		{
			new FileSaveAs(this.view).actionPerformed(e);
		}
		else
		{
			this.view.getController().saveFile();
		}
	}
}

class FileSaveAs implements ActionListener
{
	private View view;
        
	public FileSaveAs(View view)
	{
		this.view = view;
	}
        
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser fileSave = new JFileChooser();
		fileSave.setFileFilter(new OpenFileFilter(".scar"));
                
		int ret = fileSave.showDialog(view, "Save As");
		                
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			MainController controller = view.getController();
            
			File file;
			
			if (fileSave.getSelectedFile().toString().contains(".scar"))
			{
				file = fileSave.getSelectedFile();
			}
			else
			{
				file = new File(fileSave.getSelectedFile().toString() + ".scar");
			}
			//File file = fileSave.getSelectedFile();
			controller.saveFileAs(file);
        
			String[] fileName = file.getName().split("\\.");
			String tempFileName = fileName[0];
        
			for (int i = 1; i < fileName.length; i++)
			{
                 
                 if (i != fileName.length - 1)
                 {
                         tempFileName += fileName[i];
                 }
			}
        
			this.view.changeTitle(tempFileName);
        }
	}
}

class FilePrint implements ActionListener
{
	private View view;
	
	public FilePrint(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		MessageFormat header = new MessageFormat("Page {0,number,integer}");
		try 
		{
		    if(!this.view.getTable().print(JTable.PrintMode.FIT_WIDTH, header, null) && MainController.DEBUG)
		    {
		    	System.err.println("User cancelled printing");
		    }
		} 
		catch (PrinterException ex) 
		{
			if(MainController.DEBUG)
			{
				System.err.format("Cannot print %s%n", ex.getMessage());
			}
		}
	}
}

class FileExit extends WindowAdapter implements ActionListener
{
	private View view;
	
	public FileExit(View view)
	{
		this.view = view;
	}
	
    public void windowClosing(WindowEvent e) {
        this.check();
    }
	
	public void actionPerformed(ActionEvent e) {
		this.check();
	}
	
	public void check()
	{
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if(this.view.getController().getFilename() == null)
			{
				if (this.view.newDocument - 1 == 0)
				{
					fileName = "New Spreadsheet";
				}
				else
				{
					fileName = "New Spreadsheet" + (this.view.newDocument - 1);
				}
			}
			else
			{
				fileName = this.view.getController().getFilename().getName();
			}
			
			Object[] options = 
			{
					"Save",
					"Don't Save",
					"Cancel"
            };
			
			int n = JOptionPane.showOptionDialog(
				this.view,
			    "Do you want to save your changes to " + fileName + "?",
			    this.view.getApplicationTitle(),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]
			 );
			
			if(n == 0)
			{
				new FileSave(this.view).actionPerformed(null);
				this.function();
			}
			else if(n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
				// Cancel
			}
		}
	}
	
	public void function()
	{
		System.exit(0);
	}
}

class TextFieldUpdate extends MouseAdapter
{
	
	private View view;
	
	public TextFieldUpdate(View view)
	{
		this.view = view;
	}
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    	int selectedColumn = this.view.getTable().columnAtPoint(e.getPoint());
        int selectedRow = this.view.getTable().rowAtPoint(e.getPoint());
            
        try 
        {
			this.view.selectionIndicator.setText(Alfabet.parseInt(selectedColumn + 1) + (selectedRow + 1));
        } 
        catch (NumberOutOfBoundsException e1) 
        {
		}
            
        //String displayString = (String) getTable().getValueAt(selectedRow, selectedColumn);
        try 
        {
        	this.view.textField.setText(this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
        }
        catch(NullPointerException e1)
        {
        	this.view.textField.setText("");
        }
            
    }
}

class TableUpdate implements TableModelListener
{
	
	private View view;
	
	public TableUpdate(View view)
	{
		this.view = view;
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{
		
		if (this.view.openDocument == false)
		{
			int selectedColumn = e.getColumn();
			int selectedRow = e.getFirstRow();
			
			String changedValue = (String) this.view.getTable().getValueAt(selectedRow, selectedColumn);
			
			try
			{
				Cell cell = this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1);
				
				if (!cell.getValue().equals(changedValue))
				{
					cell.setFormula(changedValue);
					cell.setState(Cell.EDITED);
					this.view.textField.setText(this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
				}
					
			}
			catch(NullPointerException e1)
			{
				if (!changedValue.equals(""))
				{
					try 
					{
						Cell newCell = new Cell(changedValue);
						newCell.setState(Cell.EDITED);
						this.view.getController().getSheet().setCell(newCell, selectedColumn + 1, selectedRow + 1);
						this.view.textField.setText(this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
					} 
					catch (IndexOutOfBoundsException | NullObjectException e2) 
					{
						// Throw exception
					}
				}
			}
			
			try 
			{
				this.view.getController().getSheet().parse();
				this.view.reloadTable();
			} 
			catch (CharacterOutOfBoundsException | IllegalFormulaException e1) 
			{
				// Throw exception
			}
		}
	}
	
}

