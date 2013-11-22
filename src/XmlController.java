import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 
 * @author m.olsthoorn
 * 
 */
public class XmlController 
{

	public XmlController() 
	{		
	}
	
	//De main is om zelf te kijken of het werkt
	public static void main(String[] args) {
		XmlController xc = new XmlController();
		Sheet sheet = new Sheet();
		xc.Write(sheet, "test.xml");
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

	public void Write(Sheet sheetObject, String filename) 
	{
		try 
		{
			int rows = Sheet.getRows();
			int columns = Sheet.getColumns();			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;			
			docBuilder = docFactory.newDocumentBuilder();			 
		
			Document doc = docBuilder.newDocument();
			Element sheetElement = doc.createElement("sheet");
			doc.appendChild(sheetElement);	 
			
			int j = 0;
			for (int i = 0; i < rows; i++)
			{
				//Initialize column counter j to 0
				j = 0;
				//Create element <cell column="j" row="i"> and add cell as child to sheet
				createCell(sheetObject, doc, sheetElement, i, j);
				for(j = 0; j < columns; j++)
				{
					//Create element <cell column="j" row="i"> and add cell as child to sheet
					createCell(sheetObject, doc, sheetElement, i, j);
				}
			}
			// write the content into xml file			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));			
				
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);			 
			System.out.println("File saved!");
		}
		catch (ParserConfigurationException e) 
		{			
			e.printStackTrace();
		}
		catch (TransformerConfigurationException e)
		{			
			e.printStackTrace();
		}
		catch (TransformerException e) 
		{			
			e.printStackTrace();
		}
	}
	
	private void createCell(Sheet sheetObject, Document doc, Element sheetElement, int i, int j)
	{
		int row = i+1;		//Java starts counting by 0
		int column = j+1;	//Java starts counting by 0		
		Element cell = doc.createElement("cell");
		String cellstr = "";
		cellstr += row;
		cell.setAttribute("row", cellstr);		
		cellstr = "";
		cellstr += column;
		cell.setAttribute("column", cellstr);
		sheetElement.appendChild(cell);
		Element content = doc.createElement("content");
		content.appendChild(doc.createTextNode(sheetObject.getContent(row, column)));
		cell.appendChild(content);
	}
	
	

}
