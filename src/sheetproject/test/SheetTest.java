package sheetproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class SheetTest {

	@Test
	public void testSheet() throws IndexOutOfBoundsException
	{
		Sheet a = new Sheet();
		assertFalse(a.getCells().equals(null));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetCell1() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(-1, 1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetCell2() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(1000000000, 1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetCell3() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(1, -1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetCell4() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(1, 1000000000));
	}
	
	@Test
	public void testGetCell5() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(1, 1));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetCell() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 0, 1);
		assertEquals(b, a.getCell(0, 1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetCell2() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1000000000, 1);
		assertEquals(b, a.getCell(1000000000, 1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetCell3() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 0);
		assertEquals(b, a.getCell(1, 0));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetCell4() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1000000000);
		assertEquals(b, a.getCell(1, 1000000000));
	}
	
	@Test
	public void testSetCell5() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		assertEquals(b, a.getCell(1, 1));
	}
	
	@Test(expected=NullObjectException.class)
	public void testSetCell6() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(null, 1, 1);
		assertEquals(null, a.getCell(1, 1));
	}
	
	@Test
	public void testParse() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		a.parse();
	}
	
	@Test
	public void testParse2() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		a.parse();
	}

	@Test
	public void testEquals() throws IndexOutOfBoundsException
	{
		Sheet a = new Sheet();
		String b = "";
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals2() throws IndexOutOfBoundsException
	{
		Sheet a = new Sheet();
		assertFalse(a.equals(null));
	}
	
	@Test
	public void testEquals3() throws IndexOutOfBoundsException
	{
		Sheet a = new Sheet();
		Sheet b = new Sheet();
		assertTrue(a.equals(b));
	}
	
	@Test
	public void testEquals4() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Sheet b = new Sheet();
		Cell c = new Cell("test");
		Cell d = new Cell("test");
		a.setCell(c, 1, 1);
		b.setCell(d, 1, 1);
		assertTrue(a.equals(b));
	}
	
	@Test
	public void testEquals5() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Sheet b = new Sheet();
		Cell c = new Cell("test");
		Cell d = new Cell("test2");
		a.setCell(c, 1, 1);
		b.setCell(d, 1, 1);
		assertFalse(a.equals(b));
	}

	@Test
	public void testToString() throws IndexOutOfBoundsException
	{
		Sheet a = new Sheet();
		assertEquals("", a.toString());
	}
	
	@Test
	public void testToString2() throws IndexOutOfBoundsException, NullObjectException
	{
		Sheet a = new Sheet();
		Cell c = new Cell("test");
		Cell d = new Cell("test2");
		a.setCell(c, 1, 1);
		a.setCell(d, 1, 2);
		assertEquals("test test2", a.toString());
	}

}
