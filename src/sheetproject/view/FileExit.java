package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class FileExit extends WindowAdapter implements ActionListener
{
	private View view;
	
	public FileExit(View view)
	{
		this.view = view;
	}
	
    public void windowClosing(WindowEvent e) 
    {
        this.check();
    }
	
	public void actionPerformed(ActionEvent e) 
	{
		this.check();
	}
	
	public void check()
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
				new FileSave(this.view).actionPerformed(null);
				this.function();
			}
			else if(n == 1)
			{
				this.function();
			}
			else if (n == 2)
			{
				// Cancel
			}
		}
	}
	
	public void function()
	{
		System.exit(0);
	}
}
