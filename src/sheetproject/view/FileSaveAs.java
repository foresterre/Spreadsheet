package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import sheetproject.controller.MainController;

/**
 * Class for saving a file as a new name
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class FileSaveAs implements ActionListener
{
	/**
	 * Local view reference
	 */
	private View view;
    
	/**
	 * Constructor method for FileSaveAs
	 * 
	 * @param view View to pass through
	 */
	public FileSaveAs(View view)
	{
		this.view = view;
	}
    
	/**
	 * On action performed event for FileSaveAs
	 */
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser fileSave = new JFileChooser();
		fileSave.setFileFilter(new OpenFileFilter(".scar"));
                
		int ret = fileSave.showDialog(view, "Save As");
		                
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			MainController controller = view.getController();
            
			File file;
			
			if (fileSave.getSelectedFile().toString().endsWith(".scar"))
			{
				file = fileSave.getSelectedFile();
			}
			else if(fileSave.getSelectedFile().toString().endsWith(".xml"))
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
