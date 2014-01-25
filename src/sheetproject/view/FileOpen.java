package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import sheetproject.controller.MainController;

public class FileOpen implements ActionListener 
{
	private View view;
	
	public FileOpen(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if(this.view.getController().getFilename() == null)
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
				fileName = this.view.getController().getFilename().getName();
			}
			
			Object[] options = 
			{
					"Save",
					"Don't Save",
					"Cancel"
            };
			
			int n = JOptionPane.showOptionDialog(
				this.view,
			    "Do you want to save your changes to " + fileName + "?",
			    this.view.getApplicationTitle(),
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[2]
			 );
			
			if(n == 0)
			{
				new FileSave(this.view).actionPerformed(e);
				this.function();
			}
			else if(n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
			}
		}
	}
	
	public void function()
	{		
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.setFileFilter(new OpenFileFilter(".scar"));
		
		int ret = fileOpen.showDialog(view, "Open");
		
		if (ret == JFileChooser.APPROVE_OPTION)
        {
			MainController controller = view.getController();
			
			this.view.clearTable();
			
	        File file = fileOpen.getSelectedFile();
	        controller.openFile(file);
	        
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
	        
	        this.view.reloadTable();
        }
	}
}
