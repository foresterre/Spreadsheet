import static org.junit.Assert.*;

import org.junit.Test;


public class XmlDriverTest {

	
	public void testRead() throws FileCorruptException {
		
		Sheet sheet = XmlDriver.read("testRead.xml");
		for (int i = 1; i < Sheet.getColumns(); i++) 
		{
			for (int j = 1; j < Sheet.getColumns(); j++) 
			{
				Cell cell = sheet.getCell(i, j);
				assertEquals("text", cell.getContent());
			}
		}
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorrupt() throws FileCorruptException {
		
		Sheet sheet = XmlDriver.read("testReadCorrupt.xml");
		for (int i = 1; i < Sheet.getColumns(); i++) 
		{
			for (int j = 1; j < Sheet.getColumns(); j++) 
			{
				Cell cell = sheet.getCell(i, j);
				assertEquals("text", cell.getContent());
			}
		}
	}

	@Test
	public void testWrite() {
		//XmlDriver xc = new XmlDriver();
		//Sheet sheet = new Sheet();
		//xc.Write(sheet, "test-write.xml");
	}

}
