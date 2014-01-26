package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JTable;

import sheetproject.controller.MainController;

/**
 * Class for printing a file
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FilePrint implements ActionListener
{
	/**
	 * Variable for the view object
	 */
	private View view;

	/**
	 * Constructor for the FilePrint method
	 * @param view: give through object
	 */
	public FilePrint(View view)
	{
		this.view = view;
	}

	/**
	 * On action performed event for FilePrint
	 */
	public void actionPerformed(ActionEvent e)
	{
		MessageFormat header = new MessageFormat("Page {0,number,integer}");
		try
		{
			if (!this.view.getTable().print(JTable.PrintMode.FIT_WIDTH, header, null) && MainController.DEBUG)
			{
				System.err.println("User cancelled printing");
			}
		}
		catch (PrinterException ex)
		{
			if (MainController.DEBUG)
			{
				System.err.format("Cannot print %s%n", ex.getMessage());
			}
		}
	}
}
