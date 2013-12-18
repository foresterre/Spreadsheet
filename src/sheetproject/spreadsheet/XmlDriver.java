package sheetproject.spreadsheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.FileCorruptException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;

/**
 * Class for interfacing with a XML file
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class XmlDriver
{	
	
	/**
	 * Method for reading a XML file into a sheet object
	 * 
	 * @param filename String Filename of the file to read
	 * @return Sheet Sheet object containing the contents of the file
	 * @throws FileCorruptException 
	 * @throws FileNotFoundException 
	 * @throws NullObjectException 
	 * @throws IndexOutOfBoundsException 
	 * @throws CharacterOutOfBoundsException 
	 * @throws IllegalFormulaException 
	 */
	public static Sheet read(File filename) throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{		
		
		// get the rows and columns from the Sheet object
		int columns = Sheet.getColumns();	
		int rows = Sheet.getRows();
		
		Sheet sheet = new Sheet();
		
		try
		{
			
			// prepare for reading into objects
			//File xmlFile = new File(filename);
			File xmlFile = filename;
			DocumentBuilderFactory sheetDocBuildFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder sheetDocBuild = sheetDocBuildFac.newDocumentBuilder();
			Document sheetDoc = sheetDocBuild.parse(xmlFile);
			sheetDoc.getDocumentElement().normalize();
			
			// check duplicates array
			ArrayList<String> check = new ArrayList<String>();
			
			// read xml file
			NodeList nodeList = sheetDoc.getElementsByTagName("CELL");
			
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					String content = element.getTextContent().replaceAll("\n", "");
					
					Cell cell = new Cell(content);
					int x;
					int y;
					
					// Throw a exception when a entry has a too high column number
					try
					{
						x = Integer.parseInt(element.getAttribute("column"));
					}
					catch(NumberFormatException e)
					{
						throw new FileCorruptException("The xml file has a entry with a too high column number or is not a number. Max is " + Integer.MAX_VALUE);
					}
					
					// Throw an exception if the xml file has a negative column number
					if (x < 1)
					{
						throw new FileCorruptException("The xml file has a negative column number");
					}
					
					// Throw an exception if the xml file has to many columns
					if (x > columns)
					{
						throw new FileCorruptException("The xml file has to many columns. Max is " + columns);
					}
										
					// Throw a exception when a entry has a too high row number
					try
					{
						y = Integer.parseInt(element.getAttribute("row"));
					}
					catch(NumberFormatException e)
					{
						throw new FileCorruptException("The xml file has a entry with a too high row number or is not a number. Max is " + Integer.MAX_VALUE);
					}
					
					// Throw an exception if the xml file has a negative row number
					if (y < 1)
					{
						throw new FileCorruptException("The xml file has a negative row number");
					}
					
					// Throw an exception if the xml file has to many rows
					if (y > rows)
					{
						throw new FileCorruptException("The xml file has to many rows. Max is " + rows);
					}
					
					// Check for duplicates
					if (check.contains(x + "," + y)){
						throw new FileCorruptException("XML contains duplicate cells");
					}
					
					sheet.setCell(cell, x, y);
					check.add(x + "," + y);
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
		sheet.parse();
		return sheet;
		
	}

	/**
	 * Method for writing to a file
	 * @param sheetObject The data which will be written to the XML file
	 * @param filename The XML file which to which will be written
	 */
	public void write(Sheet sheetObject, File filename) 
	{
		try 
		{			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			 
		
			Document doc = docBuilder.newDocument();
			Element sheetElement = doc.createElement("SPREADSHEET");
			doc.appendChild(sheetElement);	 
			
			for (String key : sheetObject.getCells().keySet())
			{

				// Create cell Element
				Element cell = doc.createElement("CELL");
				
				// Getting Coords
				String[] coords = key.split(",");
				
				// Add row attrib to cell
				cell.setAttribute("row", coords[1]);		
				
				// Add column attrib to cell
				cell.setAttribute("column", coords[0]);
				
				// Set content
				cell.setTextContent(sheetObject.getCells().get(key).getFormula());
						
				// Add the child "cell" to the sheetElement
				sheetElement.appendChild(cell);
			}
			
			// write the content into xml file			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(filename);			
				
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
}
