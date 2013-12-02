package sheetproject.gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sheetproject.spreadsheet.MainController;
import sheetproject.spreadsheet.Sheet;

public class SpreadsheetGUI extends JFrame
{

	public static void main(String[] args)
	{
		SpreadsheetGUI hallo = new SpreadsheetGUI();
		hallo.start();

	}
	
	JTable table;
	DefaultTableModel model;
	
	public void start()
	{
		
	
		model = new DefaultTableModel(Sheet.getColumns(), Sheet.getRows());
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		MainController.openFile("xml/testRead.xml");
		Sheet sheet = MainController.getSheet();
		
		for(int i = 0; i < Sheet.getColumns(); i++)
		{
			for(int j = 0; j < Sheet.getRows(); j++)
			{				
				table.setValueAt(sheet.getCell(i+1, j+1).getFormula(), i, j);
			}
		}
		
		add(pane);
		
		setVisible(true);
        setSize(500,500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

}
