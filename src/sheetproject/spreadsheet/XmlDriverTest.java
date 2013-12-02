package sheetproject.spreadsheet;
import static org.junit.Assert.*;

import org.junit.Test;


public class XmlDriverTest {

	@Test
	public void testRead() throws FileCorruptException {
		
		Sheet sheet = XmlDriver.read("xml/testRead.xml");
		for (int i = 1; i < Sheet.getColumns(); i++) 
		{
			for (int j = 1; j < Sheet.getColumns(); j++) 
			{
				Cell cell = sheet.getCell(i, j);
				assertEquals("text", cell.getFormula());
			}
		}
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorrupt() throws FileCorruptException {
		
		Sheet sheet = XmlDriver.read("xml/testReadCorrupt.xml");
		for (int i = 1; i < Sheet.getColumns(); i++) 
		{
			for (int j = 1; j < Sheet.getColumns(); j++) 
			{
				Cell cell = sheet.getCell(i, j);
				assertEquals("text", cell.getFormula());
			}
		}
	}

	@Test
	public void testWrite() {
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		xd.write(sheet, "xml/testWrite.xml");
	}

}
