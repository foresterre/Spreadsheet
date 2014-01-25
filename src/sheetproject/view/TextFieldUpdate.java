package sheetproject.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.NumberOutOfBoundsException;

public class TextFieldUpdate extends MouseAdapter
{
	private View view;
	
	public TextFieldUpdate(View view)
	{
		this.view = view;
	}
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    	int clickedColumn = this.view.getTable().columnAtPoint(e.getPoint());
        int clickedRow = this.view.getTable().rowAtPoint(e.getPoint());
            
        try 
        {
			this.view.selectionIndicator.setText(Alphabet.parseInt(clickedColumn + 1) + (clickedRow + 1));
        } 
        catch (NumberOutOfBoundsException e1) 
        {
		}
            
        //String displayString = (String) getTable().getValueAt(selectedRow, selectedColumn);
        try 
        {
        	this.view.textField.setText(this.view.getController().getSheet().getCell(clickedColumn + 1, clickedRow + 1).getFormula());
        }
        catch(NullPointerException e1)
        {
        	this.view.textField.setText("");
        }
            
    }
}
