import static org.junit.Assert.*;

import org.junit.Test;


public class XmlControllerTest {

	@Test
	public void testRead() {
		Sheet sheet = XmlController.Read("test.xml");
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
		//XmlController xc = new XmlController();
		//Sheet sheet = new Sheet();
		//xc.Write(sheet, "test-write.xml");
	}

}
