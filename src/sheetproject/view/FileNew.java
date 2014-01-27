package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Class for exiting a file
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FileNew implements ActionListener
{

	/**
	 * Local view reference
	 */
	private View view;

	/**
	 * Constructor for the FileNew method
	 * 
	 * @param view View to pass through
	 */
	public FileNew(View view)
	{
		this.view = view;
	}

	/**
	 * Method to check whether an action was performed or not
	 */
	public void actionPerformed(ActionEvent e)
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

			int n = JOptionPane.showOptionDialog(this.view, "Want to save your changes to " + fileName + "?", this.view.getApplicationTitle(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			if (n == 0)
			{
				new FileSave(this.view).actionPerformed(e);
				this.function();
			}
			else if (n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
			}
		}
	}

	/**
	 * Helper method
	 */
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
