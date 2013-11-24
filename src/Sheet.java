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
	static int columns = 10;
	
	/**
	 * Static variable containing the number of rows in the table
	 */
	static int rows = 20;
	
	/**
	 * Array containing the cells of the table 
	 */
	private Cell[][] cells;
	
	/**
	 * Constructor
	 * 
	 * Fills the array initially with empty cells
	 */
	public Sheet() 
	{
		cells = new Cell[columns][rows];
		for(int x = 0; x < columns; x++)
		{
			for(int y = 0; y < rows; y++)
			{
				cells[x][y] = new Cell();
			}
		}
		
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
	 * Method that returns the content of a cell
	 * 
	 * @param column int Integer representing the column
	 * @param row int Integer representing the row
	 * @return String the content of the cell
	 */
	public String getContent(int column, int row)
	{		
		return cells[column-1][row-1].getContent();
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
		return cells[column-1][row-1];
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
		this.cells[column-1][row-1] = cell;
	}

}
