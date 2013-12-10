package sheetproject.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import sheetproject.spreadsheet.Cell;

public class CellTest {

	@Test
	public void testCell()
	{
		Cell a = new Cell();
		assertEquals("", a.getFormula());
		assertEquals(0, a.getState());
		assertEquals(Color.black, a.getForeground());
		assertEquals(Color.white, a.getBackground());
	}
	
	@Test
	public void testCell2()
	{
		Cell a = new Cell("test");
		assertEquals("test", a.getFormula());
		assertEquals(0, a.getState());
		assertEquals(Color.black, a.getForeground());
		assertEquals(Color.white, a.getBackground());
	}
	
	@Test
	public void testCell3()
	{
		Cell a = new Cell("test", 0, Color.black, Color.white);
		assertEquals("test", a.getFormula());
		assertEquals(0, a.getState());
		assertEquals(Color.black, a.getForeground());
		assertEquals(Color.white, a.getBackground());
	}
	
	@Test
	public void testSetValue()
	{
		Cell a = new Cell();
		a.setValue("test2");
		assertEquals("test2", a.getValue());
	}
	
	@Test
	public void testSetFormula()
	{
		Cell a = new Cell();
		a.setFormula("test2");
		assertEquals("test2", a.getFormula());
	}
	
	@Test
	public void testSetState()
	{
		Cell a = new Cell();
		a.setState(1);
		assertEquals(1, a.getState());
	}
	
	@Test
	public void testSetForeground()
	{
		Cell a = new Cell();
		a.setForeground(Color.black);
		assertEquals(Color.black, a.getForeground());
	}
	
	@Test
	public void testSetBackground()
	{
		Cell a = new Cell();
		a.setBackground(Color.white);
		assertEquals(Color.white, a.getBackground());
	}
	
	@Test
	public void testParse()
	{
		Cell a = new Cell("parse this");
		assertEquals("parse this", a.getValue());
	}
	
	@Test
	public void testToString() 
	{
		Cell a = new Cell("test");
		assertEquals("test", a.toString());
	}

	@Test
	public void testEquals() 
	{
		Cell a = new Cell("test");
		String b = "";
		
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals2() 
	{
		Cell a = new Cell("test", 0, Color.black, Color.white);
		Cell b = new Cell("test2", 0, Color.black, Color.white);
		
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals3() 
	{
		Cell a = new Cell("test", 0, Color.black, Color.white);
		Cell b = new Cell("test", 1, Color.black, Color.white);
		
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals4() 
	{
		Cell a = new Cell("test", 0, Color.white, Color.white);
		Cell b = new Cell("test", 0, Color.black, Color.white);
		
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals5() 
	{
		Cell a = new Cell("test", 0, Color.black, Color.black);
		Cell b = new Cell("test", 0, Color.black, Color.white);
		
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testEquals6() 
	{
		Cell a = new Cell("test", 0, Color.black, Color.white);
		Cell b = new Cell("test", 0, Color.black, Color.white);
		
		assertTrue(a.equals(b));
	}

}
