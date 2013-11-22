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
	}
	
	public static int getRows()
	{
		return rows;
	}
	
	public static int getColumns()
	{
		return columns;
	}

}
