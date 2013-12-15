package sheetproject.spreadsheet;

import java.util.HashMap;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;

/**
 * Class representing a table of cells
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Sheet 
{
	
	/**
	 * Static variable containing the number of columns in the table
	 */
	final static int columns = 10;
	
	/**
	 * Static variable containing the number of rows in the table
	 */
	final static int rows = 20;
	
	/**
	 * HashMap containing the cells of the table 
	 */
	private HashMap<String, Cell> cells;
	
	/**
	 * Constructor
	 * 
	 * Constructs the HashMap
	 */
	public Sheet() 
	{
		this.cells = new HashMap<String, Cell>();
		
	}
	
	/**
	 * Method that returns the number of columns of the table
	 * 
	 * @return int Number of columns
	 */
	public static int getColumns()
	{
		return columns;
	}
	
	/**
	 * Method that returns the number of rows of the table
	 * 
	 * @return int Number of rows
	 */
	public static int getRows()
	{
		return rows;
	}
	
	/**
	 * Method that returns the cells
	 * 
	 * @return HashMap Containing the cells
	 */
	public HashMap<String, Cell> getCells()
	{		
		return this.cells;
	}	
	
	/**
	 * Method that returns the cell
	 * 
	 * @param column int Integer representing the column
	 * @param row int Integer representing the row
	 * @throws IndexOutOfBoundsException
	 * @return Cell The cell
	 */
	public Cell getCell(int column, int row) throws IndexOutOfBoundsException
	{	
		if (column < 1)
		{
			throw new IndexOutOfBoundsException("The column index is too low");
		}
		if (column > Sheet.getColumns())
		{
			throw new IndexOutOfBoundsException("The column index is too high");
		}
		if (row < 1)
		{
			throw new IndexOutOfBoundsException("The row index is too low");
		}
		if (row > Sheet.getRows())
		{
			throw new IndexOutOfBoundsException("The row index is too high");
		}
		
		return this.cells.get(column + "," + row);
	}	
	
	/**
	 * Method that sets the cell
	 * 
	 * @param cell Cell The cell
	 * @param column int Integer representing the column
	 * @param row int Integer representing the row
	 * @throws IndexOutOfBoundsException
	 * @throws NullObjectException 
	 */
	public void setCell(Cell cell, int column, int row) throws IndexOutOfBoundsException, NullObjectException
	{
		if (cell != null)
		{
			if (column < 1)
			{
				throw new IndexOutOfBoundsException("The column index is too low");
			}
			else if (column > Sheet.getColumns())
			{
				throw new IndexOutOfBoundsException("The column index is too high");
			}
			else if (row < 1)
			{
				throw new IndexOutOfBoundsException("The row index is too low");
			}
			else if (row > Sheet.getRows())
			{
				throw new IndexOutOfBoundsException("The row index is too high");
			}
			
			this.cells.put(column + "," + row, cell);
		}
		else
		{
			throw new NullObjectException();
		}
	}
	
	/**
	 * Parses the cells
	 * @throws CharacterOutOfBoundsException 
	 * @throws IllegalFormulaException 
	 */
	public void parse() throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		for(String key : this.cells.keySet())
		{
			this.cells.get(key).parse(this);
		}
	}
	
	/**
	 * Equals method
	 * Checks if two sheet objects are equal
	 * 
	 * @return boolean returns true if they are equal
	 */
	public boolean equals(Object other)
	{
		if(other instanceof Sheet)
		{
			Sheet that = (Sheet) other;
			for(String key : this.cells.keySet())
			{
				if(!this.cells.get(key).equals(that.cells.get(key)))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	* toString method 
	* Visualizes the content of the xml file
	*
	* @return String returns a representation of the xml content with its values
	*/
	public String toString()
    {

            String returnString = "";

            for(String key : this.cells.keySet()) 
            {
            	returnString = returnString + " " + this.cells.get(key).toString();
            }
            
            return returnString;
    }

}
