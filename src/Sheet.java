/**
 * 
 * @author m.olsthoorn
 *
 */
public class Sheet 
{
	static int columns = 10;
	static int rows = 20;
	
	
	Cell[][] cells;
	
	public Sheet() 
	{
		cells = new Cell[columns][rows];
		for(int i = 0; i < columns; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				cells[i][j] = new Cell();
			}
		}
		
	}
	
	public static int getColumns()
	{
		return columns;
	}
	
	public static int getRows()
	{
		return rows;
	}
		
	public String getContent(int column, int row)
	{		
		return cells[column-1][row-1].getContent();
	}
	
	public Cell getCell(int column, int row)
	{		
		return cells[column-1][row-1];
	}
	
	public void setCell(Cell cell, int column, int row)
	{
		this.cells[column-1][row-1] = cell;
	}

}
