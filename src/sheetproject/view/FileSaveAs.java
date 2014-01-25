package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import sheetproject.controller.MainController;

public class FileSaveAs implements ActionListener
{
	private View view;
    
	public FileSaveAs(View view)
	{
		this.view = view;
	}
        
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser fileSave = new JFileChooser();
		fileSave.setFileFilter(new OpenFileFilter(".scar"));
                
		int ret = fileSave.showDialog(view, "Save As");
		                
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			MainController controller = view.getController();
            
			File file;
			
			if (fileSave.getSelectedFile().toString().contains(".scar"))
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
