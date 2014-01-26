package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for saving a file
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FileSave implements ActionListener 
{
	/**
	 * Variable for the view object
	 */
	private View view;
	
	/**
	 * Constructor for the FileSave method
	 * @param view: give-through object
	 */
	public FileSave(View view)
	{
		this.view = view;
	}
	
	/**
	 * On action performed event for FileSave
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		if(this.view.getController().getFileName() == null)
		{
			new FileSaveAs(this.view).actionPerformed(e);
		}
		else
		{
			this.view.getController().saveFile();
		}
	}
}
