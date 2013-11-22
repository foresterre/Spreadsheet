/**
 * 
 * @author m.olsthoorn
 *
 */
public class Cell 
{

	/**
	 * 
	 */
	private String content;
	
	public Cell(String content) 
	{
		this.content = content;
	}

	public Cell() {
		content = "text";
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() 
	{
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) 
	{
		this.content = content;
	}
	
}
