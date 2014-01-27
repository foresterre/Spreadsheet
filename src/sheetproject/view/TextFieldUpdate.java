package sheetproject.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.NumberOutOfBoundsException;

/**
 * Class for the TextFieldUpdate
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class TextFieldUpdate extends MouseAdapter
{
	/**
	 * Local view reference
	 */
	private View view;
	
	/**
	 * Constructor for TextFieldUpdate
	 * 
	 * @param view View to pass through
	 */
	public TextFieldUpdate(View view)
	{
		this.view = view;
	}
    
	/**
	 * Method for the mouseClicked event
	 */
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
        	// This catch statement is to catch exceptions that are not important for the executing of our application
		}
        
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
