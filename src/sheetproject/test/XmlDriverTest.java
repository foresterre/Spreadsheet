package sheetproject.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.FileCorruptException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;
import sheetproject.spreadsheet.XmlDriver;


public class XmlDriverTest {

	@Test
	public void testReadEmpty() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadEmpty.xml");
		Sheet sheet = XmlDriver.read(f);
		assertEquals(0, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooHighColumnNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptTooHighColumnNumber.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptNegativeColumnNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptNegativeColumnNumber.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooManyColumns() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptTooManyColumns.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooHighRowNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptTooHighRowNumber.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptNegativeRowNumber() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptNegativeRowNumber.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadFileCorruptTooManyRows() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadCorruptTooManyRows.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test
	public void testRead() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testRead.xml");
		Sheet sheet = XmlDriver.read(f);
		assertEquals(200, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadDuplicate() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException
	{	
		File f = new File("xml/testReadDuplicate.xml");
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testReadDoesNotExist() throws FileCorruptException, IndexOutOfBoundsException, NullObjectException, FileNotFoundException, CharacterOutOfBoundsException, IllegalFormulaException 
	{	
		File f = new File("xml/testReadDoesNotExist.xml");
		Sheet sheet = XmlDriver.read(f);
	}

	@Test
	public void testWriteEmpty() throws IndexOutOfBoundsException, FileNotFoundException, FileCorruptException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException 
	{
		File f = new File("xml/testWriteEmpty.xml");
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		xd.write(sheet, f);
		assertEquals(0, XmlDriver.read(f).getCells().size());
	}
	
	@Test
	public void testWrite() throws IndexOutOfBoundsException, NullObjectException, FileNotFoundException, FileCorruptException, CharacterOutOfBoundsException, IllegalFormulaException 
	{
		File f = new File("xml/testWrite.xml");
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		Cell a = new Cell("test");
		Cell b = new Cell("test");
		Cell c = new Cell("test");
		sheet.setCell(a, 1, 1);
		sheet.setCell(b, 1, 2);
		sheet.setCell(c, 1, 3);
		xd.write(sheet, f);
		assertEquals(3, XmlDriver.read(f).getCells().size());
	}

}
