package sheetproject.spreadsheet;

import java.awt.Color;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.formula.Parser;

/**
 * Class representing a cell in a spreadsheet table
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Cell
{

	public static final int UPTODATE = 0;
	public static final int EDITED = 1;

	/**
	 * Variable containing the value of the cell
	 */
	private String value;

	/**
	 * Variable containing the formula of the cell
	 */
	private String formula;

	/**
	 * Variable containing the state of the cell
	 */
	private int state;

	/**
	 * Variable containing the foreground color of the cell
	 */
	private Color foreground;

	/**
	 * Variable containing the background color of the cell
	 */
	private Color background;

	/**
	 * Constructor for the cell class
	 * 
	 */
	public Cell()
	{
		this("", 0, Color.black, Color.white);
	}
	
	/**
	 * Constructor for the cell class 
	 * @param content String containing the content of the cell
	 */
	public Cell(String content)
	{
		this(content, 0, Color.black, Color.white);
	}

	/**
	 * Constructor for the cell class
	 * @param content String containing the content of the cell
	 * @param state State of the cell
	 * @param foreground Foreground color of the cell
	 * @param background Background color of the cell
	 */
	public Cell(String content, int state, Color foreground, Color background)
	{
		this.formula = content;
		this.state = state;
		this.foreground = foreground;
		this.background = background;
	}

	/**
	 * Method that returns the value of the cell
	 * 
	 * @return The value of the cell
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Method that sets the value of the cell
	 * 
	 * @param value The value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Method that returns the formula of the cell
	 * 
	 * @return Content of the cell
	 */
	public String getFormula()
	{
		return this.formula;
	}

	/**
	 * Method that sets the formula of the cell
	 * 
	 * @param formula Formula for the cell
	 */
	public void setFormula(String formula)
	{
		this.formula = formula;
	}

	/**
	 * Method that returns the state of the cell
	 * 
	 * @return The state of the cell
	 */
	public int getState()
	{
		return state;
	}

	/**
	 * Method that sets the state of the cell
	 * 
	 * @param state The state to set
	 */
	public void setState(int state)
	{
		this.state = state;
	}

	/**
	 * Method that returns the foreground color of the cell
	 * 
	 * @return The foreground color
	 */
	public Color getForeground()
	{
		return foreground;
	}

	/**
	 * Method that sets the foreground color of the cell
	 * 
	 * @param foreground The foreground color to set
	 */
	public void setForeground(Color foreground)
	{
		this.foreground = foreground;
	}

	/**
	 * Method that returns the background color of the cell
	 * 
	 * @return The background color
	 */
	public Color getBackground()
	{
		return background;
	}

	/**
	 * Method that sets the background color of the cell
	 * 
	 * @param background The background color to set
	 */
	public void setBackground(Color background)
	{
		this.background = background;
	}

	/**
	 * Method for parsing the formula of the cell
	 * 
	 * @param sheet The sheet with the data for parsing
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public void parse(Sheet sheet) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		this.value = Parser.parse(this.formula, sheet);
	}

	/**
	 * ToString method
	 * 
	 * Returns a string representation of the cell object
	 * 
	 * @return String representation of the cell
	 */
	public String toString()
	{
		return this.formula;
	}

	/**
	 * Equals method Checks if two cell objects are equal
	 * 
	 * @return Returns true if they are equal, false otherwise
	 */
	public boolean equals(Object other)
	{
		if (other instanceof Cell)
		{
			Cell that = (Cell) other;
			if (that.formula.equals(this.formula) && that.state == this.state
					&& that.foreground.equals(this.foreground) && that.background.equals(this.background))
			{
				return true;
			}
		}
		return false;
	}
}
