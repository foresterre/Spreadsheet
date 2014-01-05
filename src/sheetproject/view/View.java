package sheetproject.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.MessageFormat;




import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import sheetproject.controller.MainController;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.alfabet.Alfabet;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class View extends JFrame 
{
	
	private MainController controller;
	
	private String applicationTitle = "Scarlet";
	
	private String applicationImageLocation = "applicationIcon-128x128.png";
	
	private JToolBar toolbar;
	
	private JTable table;
	
	private JPanel statusPanel;
	
	private JLabel statusLabel;
	
	private JTextField selectionIndicator;
	
	private JTextField textField;
	
	
	
	public View(MainController controller)
	{
		// Set visibility to TRUE
		this.setVisible(true);
		
		// Set default closing action
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		this.setupTable();
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
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
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
        fileExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        	
        });
 
        // Add menu items to file menu bar item
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
        selectionIndicator.setPreferredSize(new Dimension(10, 24));
        this.toolbar.add(selectionIndicator);
        
        textField = new JTextField();
        this.toolbar.add(textField);
        
        this.add(this.toolbar, BorderLayout.PAGE_START);
        
        this.statusPanel = new JPanel();
        this.add(this.statusPanel, BorderLayout.SOUTH);
        this.statusPanel.setPreferredSize(new Dimension(this.getWidth(), 24));
        this.statusPanel.setLayout(new BoxLayout(this.statusPanel, BoxLayout.X_AXIS));
        this.statusLabel = new JLabel("status");
        this.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.statusPanel.add(this.statusLabel);
	}
	
	public void setupTable()
	{
		// Setup default table
		this.setTable(new JTable(new DefaultTableModel(Sheet.getRows(),Sheet.getColumns())));
		this.getTable().setShowGrid(true);
		this.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTable().setFillsViewportHeight(true);
		this.getTable().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.getTable().setCellSelectionEnabled(true);
		
		this.getTable().addMouseListener(new MouseAdapter()
		{
            
            @Override
            public void mouseClicked(MouseEvent e){
                    int selectedColumn = getTable().columnAtPoint(e.getPoint());
                    int selectedRow = getTable().rowAtPoint(e.getPoint());
                    
                    try 
                    {
						selectionIndicator.setText(Alfabet.parseInt(selectedColumn + 1) + (selectedRow + 1));
					} catch (NumberOutOfBoundsException e1) 
					{
					}
                    
                    //String displayString = (String) getTable().getValueAt(selectedRow, selectedColumn);
                    try 
                    {
                    	textField.setText(getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
                    }
                    catch(NullPointerException e1)
                    {
                    	textField.setText("");
                    }
                    
            	}
		});
		
		this.getTable().getModel().addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent e)
			{
				int selectedColumn = e.getColumn();
				int selectedRow = e.getFirstRow();
				
				String changedValue = (String) getTable().getValueAt(selectedRow, selectedColumn);
				getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).setFormula(changedValue);
				try {
					getController().getSheet().parse();
				} catch (CharacterOutOfBoundsException
						| IllegalFormulaException e1) {
				}
			}
			
		});

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
                return getTable().convertRowIndexToModel(row);
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
		 
		// Add both tables to the scroll pane
		JScrollPane scrollPane = new JScrollPane(getTable());
		scrollPane.setRowHeaderView(Tablerow);
        this.add(scrollPane, BorderLayout.CENTER);
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
		this.view.getController().newFile();
		this.view.setupTable();
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
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.setFileFilter(new OpenFileFilter(".scarlet"));
		
		int ret = fileOpen.showDialog(view, "Open");
		
		if (ret == JFileChooser.APPROVE_OPTION)
        {
			MainController controller = view.getController();
			
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
	        
	        
            
            for(String key : controller.getSheet().getCells().keySet())
            {
                    String[] index = key.split(",");
                    int columnIndex = Integer.parseInt(index[0]);
                    int rowIndex = Integer.parseInt(index[1]);
                    
                    Cell cell = controller.getSheet().getCells().get(key);
                    String value = cell.getValue();
                    
                    this.view.getTable().setValueAt(value, rowIndex -1, columnIndex -1);         
            }
        }
	}
}

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
			new FileSaveAs(this.view);
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
	
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileSave = new JFileChooser();
		fileSave.setFileFilter(new OpenFileFilter(".scarlet"));
		
		int ret = fileSave.showDialog(view, "Save As");
		
		if (ret == JFileChooser.APPROVE_OPTION)
        {
			MainController controller = view.getController();
			
	        File file = fileSave.getSelectedFile();
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
	
	public void actionPerformed(ActionEvent e) {
		MessageFormat header = new MessageFormat("Page {0,number,integer}");
		try 
		{
		    if(!this.view.getTable().print(JTable.PrintMode.FIT_WIDTH, header, null) && MainController.DEBUG)
		    {
		    	System.err.println("User cancelled printing");
		    }
		} catch (PrinterException ex) {
		    System.err.format("Cannot print %s%n", ex.getMessage());
		}
	}
}

//class SharedListSelectionHandler implements ListSelectionListener {
//    public void valueChanged(ListSelectionEvent e) { 
//        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//
//        int firstIndex = e.getFirstIndex();
//        int lastIndex = e.getLastIndex();
//        boolean isAdjusting = e.getValueIsAdjusting(); 
//        output.append("Event for indexes "
//                      + firstIndex + " - " + lastIndex
//                      + "; isAdjusting is " + isAdjusting
//                      + "; selected indexes:");
//
//        if (lsm.isSelectionEmpty()) {
//            output.append(" <none>");
//        } else {
//            // Find out which indexes are selected.
//            int minIndex = lsm.getMinSelectionIndex();
//            int maxIndex = lsm.getMaxSelectionIndex();
//            for (int i = minIndex; i <= maxIndex; i++) {
//                if (lsm.isSelectedIndex(i)) {
//                    output.append(" " + i);
//                }
//            }
//        }
//        output.append(newline);
//        output.setCaretPosition(output.getDocument().getLength());
//    }
//}
