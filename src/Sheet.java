/**
 * 
 * @author m.olsthoorn
 *
 */
public class Sheet 
{
	static int rows = 20;
	static int columns = 10;
	
	private Cell[][] cells;
	
	public Sheet() 
	{
		cells = new Cell[rows][columns];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				cells[i][j] = new Cell();
			}
		}
		
	}
	
	public static int getRows()
	{
		return rows;
	}
	
	public static int getColumns()
	{
		return columns;
	}
	
	public String getContent(int row, int column)
	{		
		return cells[row-1][column-1].getContent();
	}

}
