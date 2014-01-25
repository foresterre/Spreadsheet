package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class FileNew implements ActionListener
{
	private View view;
	
	public FileNew(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (!this.view.isTableChanged())
		{
			this.function();
		}
		else
		{
			String fileName = "";
			if(this.view.getController().getFileName() == null)
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
			
			Object[] options = 
			{
					"Save",
					"Don't Save",
					"Cancel"
            };
			
			int n = JOptionPane.showOptionDialog(
				this.view,
			    "Want to save your changes to " + fileName + "?",
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
