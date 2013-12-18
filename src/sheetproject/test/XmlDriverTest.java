package sheetproject.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import sheetproject.exception.FileCorruptException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;
import sheetproject.spreadsheet.XmlDriver;


public class XmlDriverTest {

	@Test
	public void testReadEmpty() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		File f = new File("xml/testReadEmpty.xml");
		Sheet sheet = XmlDriver.read("xml/testReadEmpty.xml");
		assertEquals(0, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooHighColumnNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptTooHighColumnNumber.xml");
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptNegativeColumnNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptNegativeColumnNumber.xml");
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooManyColumns() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptTooManyColumns.xml");
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooHighRowNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptTooHighRowNumber.xml");
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptNegativeRowNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptNegativeRowNumber.xml");
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooManyRows() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadCorruptTooManyRows.xml");
	}
	
	@Test
	public void testRead() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testRead.xml");
		assertEquals(200, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadDuplicate() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException
	{	
		Sheet sheet = XmlDriver.read("xml/testReadDuplicate.xml");
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testReadDoesNotExist() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException 
	{	
		Sheet sheet = XmlDriver.read("xml/testReadDoesNotExist.xml");
	}

	@Test
	public void testWriteEmpty() throws IndexOutOfBoundsException, FileNotFoundException, FileCorruptException, NullObjectException 
	{
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		xd.write(sheet, "xml/testWriteEmpty.xml");
		assertEquals(0, XmlDriver.read("xml/testWriteEmpty.xml").getCells().size());
	}
	
	@Test
	public void testWrite() throws IndexOutOfBoundsException, NullObjectException, FileNotFoundException, FileCorruptException 
	{
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		Cell a = new Cell("test");
		Cell b = new Cell("test");
		Cell c = new Cell("test");
		sheet.setCell(a, 1, 1);
		sheet.setCell(b, 1, 2);
		sheet.setCell(c, 1, 3);
		xd.write(sheet, "xml/testWrite.xml");
		assertEquals(3, XmlDriver.read("xml/testWrite.xml").getCells().size());
	}

}
