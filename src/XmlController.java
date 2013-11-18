import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
		Sheet sheet = new Sheet();
		
		try
		{
			File xmlFile = new File(filename);
			DocumentBuilderFactory sheetDocBuildFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder sheetDocBuild = sheetDocBuildFac.newDocumentBuilder();
			Document sheetDoc = sheetDocBuild.parse(xmlFile);
			
			
		} 
		catch (SAXException | IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		
		return sheet;
		
	}

	public void Write(String filename) 
	{
		File xmlFile = new File(filename);
	}

}
