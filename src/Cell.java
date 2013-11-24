/**
 * Class representing a cell in a spreadsheet table
 * 
 * @author m.olsthoorn
 */
public class Cell 
{

	/**
	 * Variable containing the content of the cell
	 */
	private String content;
	
	/**
	 * Constructor for the cell class
	 * 
	 * @param content String String containing the content of the cell 
	 */
	public Cell(String content) 
	{
		this.content = content;
	}

	public Cell() {
		this.content = "";
	}

	/**
	 * Method that returns the content of the cell
	 * 
	 * @return String Content of the cell
	 */
	public String getContent() 
	{
		return this.content;
	}

	/**
	 * Method that sets the content of the cell
	 * 
	 * @param content String New content for the cell
	 */
	public void setContent(String content) 
	{
		this.content = content;
	}
	
	public String toString()
	{
		return this.content;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof Cell)
		{
			Cell that = (Cell) other; 
			if (that.content.equals(this.content))
			{
				return true;
			}
		}
		return false;
	}
	
}
