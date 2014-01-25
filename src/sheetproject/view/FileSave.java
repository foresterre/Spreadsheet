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
	private View view;
	
	public FileSave(View view)
	{
		this.view = view;
	}
	
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
