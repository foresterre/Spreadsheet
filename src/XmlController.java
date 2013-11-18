import java.io.File;

/**
 * 
 * @author m.olsthoorn
 * 
 */
public class XmlController 
{

	/**
	 * 
	 * @param filename
	 */
	public XmlController() 
	{
		
	}

	public static Sheet Read(String filename) 
	{
		File xmlFile = new File(filename);
		Sheet sheet = new Sheet();
		return sheet;
		
	}

	public void Write(String filename) 
	{
		File xmlFile = new File(filename);
	}

}
