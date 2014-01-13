package sheetproject.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	public void testReadEmpty() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadEmpty.xml";
		Sheet sheet = XmlDriver.read(f);
		assertEquals(0, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptTooHighColumnNumber() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadCorruptTooHighColumnNumber.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptNegativeColumnNumber() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{
		String f = "xml/testReadCorruptNegativeColumnNumber.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptTooManyColumns() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{
		String f = "xml/testReadCorruptTooManyColumns.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptTooHighRowNumber() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadCorruptTooHighRowNumber.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptNegativeRowNumber() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadCorruptNegativeRowNumber.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadStringCorruptTooManyRows() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadCorruptTooManyRows.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test
	public void testRead() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testRead.xml";
		Sheet sheet = XmlDriver.read(f);
		assertEquals(200, sheet.getCells().size());
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadDuplicate() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException
	{	
		String f = "xml/testReadDuplicate.xml";
		Sheet sheet = XmlDriver.read(f);
	}
	
	@Test(expected=FileCorruptException.class)
	public void testReadDoesNotExist() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileCorruptException, FileNotFoundException 
	{	
		String f = "xml/testReadDoesNotExist.xml";
		Sheet sheet = XmlDriver.read(f);
	}

	@Test
	public void testWriteEmpty() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException 
	{
		String f = "xml/testWriteEmpty.xml";
		XmlDriver xd = new XmlDriver();
		Sheet sheet = new Sheet();
		xd.write(sheet, f);
		assertEquals(0, XmlDriver.read(f).getCells().size());
	}
	
	@Test
	public void testWrite() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException, IllegalFormulaException, FileNotFoundException, FileCorruptException 
	{
		String f = "xml/testWrite.xml";
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
