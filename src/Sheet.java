import java.util.HashMap;

/**
 * Class representing a table of cells
 * 
 * @author m.olsthoorn
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
	 * Method that returns the cell
	 * 
	 * @param column int Integer representing the column
	 * @param row int Integer representing the row
	 * @return Cell The cell
	 */
	public Cell getCell(int column, int row)
	{		
		return this.cells.get(column + "," + row);
	}	
	
	/**
	 * Method that sets the cell
	 * 
	 * @param cell Cell The cell
	 * @param column int Integer representing the column
	 * @param row int Integer representing the row
	 */
	public void setCell(Cell cell, int column, int row)
	{
		this.cells.put(column + "," + row, cell);
	}
	
	/**
	 * Parses the cells
	 */
	public void parse()
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

            for(String key : this.cells.keySet()) {
            	returnString = returnString + " " + this.cells.get(key).toString();
            }
            
            return returnString;
    }

}
