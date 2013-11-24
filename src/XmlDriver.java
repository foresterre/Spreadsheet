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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for interfacing with a XML file
 * 
 * @author m.olsthoorn
 */
public class XmlDriver 
{
	
	/**
	 * Constructor
	 */
	public XmlDriver() 
	{		
	}
	
	
	/**
	 * Method for reading a XML file into a sheet object
	 * 
	 * @param filename String Filename of the file to read
	 * @return Sheet Sheet object containing the contents of the file
	 * @throws FileCorruptException 
	 */
	public static Sheet read(String filename) throws FileCorruptException 
	{		
		
		// get the rows and columns from the Sheet object
		int columns = Sheet.getColumns();	
		int rows = Sheet.getRows();
		
		
		Sheet sheet = new Sheet();
		
		try
		{
			
			// prepare for reading into objects
			File xmlFile = new File(filename);
			DocumentBuilderFactory sheetDocBuildFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder sheetDocBuild = sheetDocBuildFac.newDocumentBuilder();
			Document sheetDoc = sheetDocBuild.parse(xmlFile);
			sheetDoc.getDocumentElement().normalize();
			
			// read xml file
			NodeList nodeList = sheetDoc.getElementsByTagName("cell");
			
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					String content = element.getElementsByTagName("content").item(0).getTextContent();
					Cell cell = new Cell(content);
					int x = Integer.parseInt(element.getAttribute("column"));
					if (x > columns)
					{
						throw new FileCorruptException("The xml file has to many columns");
					}
					int y = Integer.parseInt(element.getAttribute("row"));
					if (y > rows)
					{
						throw new FileCorruptException("The xml file has to many rows");
					}
					sheet.setCell(cell, x, y);
				}
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		
		return sheet;
		
	}

	/**
	 * Method for writing to a file
	 * @param sheetObject The data which will be written to the XML file
	 * @param filename The XML file which to which will be written
	 */
	public void write(Sheet sheetObject, String filename) 
	{
		try 
		{
			// Get the rows and columns from the Sheet object
			int columns = Sheet.getColumns();
			int rows = Sheet.getRows();
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			 
		
			Document doc = docBuilder.newDocument();
			Element sheetElement = doc.createElement("sheet");
			doc.appendChild(sheetElement);	 
			
			for (int y = 0; y < rows; y++)
			{
				for(int x = 0; x < columns; x++)
				{
					// row and column variables
					int column = x+1;	//Java starts counting by 0	
					int row = y+1;		//Java starts counting by 0
					
					
					// Create cell Element
					Element cell = doc.createElement("cell");
					
					// Create String cellstr and set it empty
					String cellstr = "";
					
					// Add row to cellstr
					cellstr = cellstr + row;
					cell.setAttribute("row", cellstr);		
					
					// Refill for column
					cellstr = "";
					cellstr = cellstr + column;
					cell.setAttribute("column", cellstr);
					
					// Add the child "cell" to the sheetElement
					sheetElement.appendChild(cell);
					
					Element content = doc.createElement("content");
					content.appendChild(doc.createTextNode(sheetObject.getCell(column, row).getContent()));
					cell.appendChild(content);
				}
			}
			
			// write the content into xml file			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));			
				
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);			 

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
	
	/**
	 * CREATECELL METHOD
	 * @param sheetObject Object which holds the information of the Cell objects
	 * @param doc
	 * @param sheetElement
	 * @param i
	 * @param j
	 */
	private void createCell(Sheet sheetObject, Document doc, Element sheetElement, int i, int j)
	{
		// row and column variables
		int row = i+1;		//Java starts counting by 0
		int column = j+1;	//Java starts counting by 0	
		
		// Create cell Element
		Element cell = doc.createElement("cell");
		
		// Create String cellstr and set it empty
		String cellstr = "";
		
		// Add row to cellstr
		cellstr = cellstr + row;
		cell.setAttribute("row", cellstr);		
		
		// Refill for column
		cellstr = "";
		cellstr = cellstr + column;
		cell.setAttribute("column", cellstr);
		
		// Add the child "cell" to the sheetElement
		sheetElement.appendChild(cell);
		
		Element content = doc.createElement("content");
		content.appendChild(doc.createTextNode(sheetObject.getCell(column, row).getContent()));
		cell.appendChild(content);
	}
}
