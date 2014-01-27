package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * Class to add the ability to ask to save the changes before closing the application
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FileExit extends WindowAdapter implements ActionListener
{

	/**
	 * Local view reference
	 */
	private View view;

	/**
	 * Constructor for FileExit method
	 * @param view View to pass through
	 */
	public FileExit(View view)
	{
		this.view = view;
	}
	
	/**
	 * Check for the windowClosing event
	 */
	public void windowClosing(WindowEvent e)
	{
		this.check();
	}

	/**
	 * Check for the actionPerformed event
	 */
	public void actionPerformed(ActionEvent e)
	{
		this.check();
	}
	
	/**
	 * Method which determines whether to ask for save on exit or not 
	 */
	public void check()
	{
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if (this.view.getController().getFileName() == null)
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
				fileName = this.view.getController().getFileName().getName();
			}

			Object[] options = { "Save", "Don't Save", "Cancel" };

			int n = JOptionPane.showOptionDialog(this.view, "Do you want to save your changes to " + fileName + "?", this.view.getApplicationTitle(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			if (n == 0)
			{
				new FileSave(this.view).actionPerformed(null);
				this.function();
			}
			else if (n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
				// Cancel
			}
		}
	}
	
	/**
	 * Exit function
	 */
	public void function()
	{
		System.exit(0);
	}
}
