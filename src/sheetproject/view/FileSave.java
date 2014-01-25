package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileSave implements ActionListener 
{
	private View view;
	
	public FileSave(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
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
