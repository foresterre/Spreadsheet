package sheetproject.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import sheetproject.controller.MainController;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

/**
 * Class for the View
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class View extends JFrame
{
	
	/**
	 * Required but unused serial
	 */
	private static final long serialVersionUID = -771894500234558973L;
	
	/**
	 * MainController variable
	 */
	private MainController controller;

	/**
	 * Application title variable
	 */
	private String applicationTitle = "Scarlett";

	/**
	 * Application icon location variable
	 */
	private String applicationImageLocation = "applicationIcon-128x128.png";

	/**
	 * Toolbar initialization
	 */
	private JToolBar toolbar;

	/**
	 * Table initialization
	 */
	private JTable table;

	/**
	 * Status panel initialization
	 */
	private JPanel statusPanel;

	/**
	 * Status label initialization
	 */
	private JLabel statusLabel;

	/**
	 * Textfield initialization
	 */
	JTextField selectionIndicator;

	/**
	 * Textfield initialization
	 */
	JTextField textField;

	/**
	 * Scroll pane initialization
	 */
	private JScrollPane scrollPane;

	/**
	 * New document variable
	 */
	int newDocument = 0;

	/**
	 * boolean for openDocument status
	 */
	boolean openDocument = false;

	/**
	 * Constructor for the View
	 * @param controller: give-through object
	 */
	public View(MainController controller)
	{
		// Style
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		// JDialog.setDefaultLookAndFeelDecorated(true);

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

	/**
	 * Layout setup method
	 */
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
		// JMenuItem Wrecking = new JMenuItem("Wreck it");
		// Wrecking.setMnemonic(KeyEvent.VK_W);
		// Wrecking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
		// Wrecking.setToolTipText("Wreck it, boy");
		// Wrecking.addActionListener(new Wrecking());

		// Add menu items to file menu bar item
		// file.add(Wrecking);
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
		textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		textField.addActionListener(new FormulaUpdate(this));
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

	/**
	 * Table setup method
	 */
	public void setupTable()
	{

		// Setup default table
		DefaultTableModel dtm = new DefaultTableModel(Sheet.getRows(), Sheet.getColumns());

		this.setTable(new JTable(dtm));
		this.getTable().setShowGrid(true);
		this.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTable().setFillsViewportHeight(true);
		this.getTable().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.getTable().setCellSelectionEnabled(true);
		this.getTable().getTableHeader().setReorderingAllowed(false);

		this.getTable().addMouseListener(new TextFieldUpdate(this));
		// this.getTable().addMouseListener(new MouseAdapter()
		// {
		//
		// @Override
		// public void mouseClicked(MouseEvent e)
		// {
		// int selectedColumn = getTable().columnAtPoint(e.getPoint());
		// int selectedRow = getTable().rowAtPoint(e.getPoint());
		//
		// try
		// {
		// selectionIndicator.setText(Alfabet.parseInt(selectedColumn + 1) + (selectedRow + 1));
		// }
		// catch (NumberOutOfBoundsException e1)
		// {
		// }
		//
		// //String displayString = (String) getTable().getValueAt(selectedRow, selectedColumn);
		// try
		// {
		// textField.setText(getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
		// }
		// catch(NullPointerException e1)
		// {
		// textField.setText("");
		// }
		//
		// }
		// });

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

	/**
	 * Table clear method
	 */
	public void clearTable()
	{
		MainController controller = this.getController();
		for (String key : controller.getSheet().getCells().keySet())
		{
			String[] index = key.split(",");
			int columnIndex = Integer.parseInt(index[0]);
			int rowIndex = Integer.parseInt(index[1]);

			this.getTable().setValueAt("", rowIndex - 1, columnIndex - 1);
		}
	}

	/**
	 * Table reload method
	 */
	public void reloadTable()
	{
		this.openDocument = true;

		for (String key : this.getController().getSheet().getCells().keySet())
		{
			String[] index = key.split(",");
			int columnIndex = Integer.parseInt(index[0]);
			int rowIndex = Integer.parseInt(index[1]);

			Cell cell = this.getController().getSheet().getCells().get(key);
			String value = cell.getValue();

			this.getTable().setValueAt(value, rowIndex - 1, columnIndex - 1);
		}

		this.openDocument = false;
	}

	/**
	 * 
	 * @return true if table is changed, otherwise false
	 */
	public boolean isTableChanged()
	{
		MainController controller = this.getController();
		for (String key : controller.getSheet().getCells().keySet())
		{
			Cell cell = controller.getSheet().getCells().get(key);
			if (cell.getState() == Cell.EDITED)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method for chaning the title to DEBUG mode or not
	 * @param title: title of the application
	 */
	public void changeTitle(String title)
	{
		if (title == null)
		{
			if (MainController.DEBUG)
			{
				this.setTitle(this.applicationTitle + " - DEBUGGING MODE");
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
				this.setTitle(title + " - " + this.applicationTitle + " - DEBUGGING MODE");
			}
			else
			{
				this.setTitle(title + " - " + this.applicationTitle);
			}
		}
	}
	
	/**
	 * Shows the message
	 * @param message: message
	 */
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Shows the warning
	 * @param message: warning message
	 */
	public void showWarning(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Shows the error
	 * @param message: error message
	 */
	public void showError(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Change the current status
	 * @param status: status message
	 */
	public void changeStatus(String status)
	{
		this.statusLabel.setText(status);
	}

	/**
	 * get the current status
	 * @return current status
	 */
	public String getStatus()
	{
		return statusLabel.getText();
	}

	/**
	 * set the current status
	 * @param status: current status message
	 */
	public void setStatus(String status)
	{
		this.statusLabel.setText(status);
	}

	/**
	 * get app title
	 * @return app title
	 */
	public String getApplicationTitle()
	{
		return applicationTitle;
	}
	
	/**
	 * set the app title
	 * @param applicationTitle: applications title
	 */
	public void setApplicationTitle(String applicationTitle)
	{
		this.applicationTitle = applicationTitle;
	}
	
	/**
	 * Give through method, get the controller
	 * @return MainController object
	 */
	public MainController getController()
	{
		return controller;
	}

	/**
	 * Set the MainController object
	 * @param controller: MainController object
	 */
	public void setController(MainController controller)
	{
		this.controller = controller;
	}
	
	/**
	 * get the table
	 * @return table
	 */
	public JTable getTable()
	{
		return table;
	}
	
	/**
	 * set the table
	 * @param table: current table object
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}
}