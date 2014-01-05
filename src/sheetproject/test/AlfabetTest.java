package sheetproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.exception.CharacterOutOfBoundsException;;


public class AlfabetTest {

	@Test
	public void testInt() throws NumberOutOfBoundsException 
	{
		assertEquals("A", Alfabet.parseInt(1));
	}
	
	@Test
	public void testInt2() throws NumberOutOfBoundsException 
	{
		assertEquals("J", Alfabet.parseInt(10));
	}
	
	@Test
	public void testInt3() throws NumberOutOfBoundsException 
	{
		assertEquals("Z", Alfabet.parseInt(26));
	}
	
	@Test(expected=NumberOutOfBoundsException.class)
	public void testInt4() throws NumberOutOfBoundsException 
	{
		assertEquals("", Alfabet.parseInt(100000));
	}
	
	@Test(expected=NumberOutOfBoundsException.class)
	public void testInt5() throws NumberOutOfBoundsException 
	{
		assertEquals("", Alfabet.parseInt(0));
	}
	
	@Test
	public void testInt6() throws NumberOutOfBoundsException 
	{
		assertEquals("AA", Alfabet.parseInt(27));
	}
	
	@Test
	public void testInt7() throws NumberOutOfBoundsException 
	{
		assertEquals("XX", Alfabet.parseInt(648));
	}
	
	@Test
	public void testString() throws CharacterOutOfBoundsException 
	{
		assertEquals(7, Alfabet.parseChar("G"));
	}
	
	@Test
	public void testString2() throws CharacterOutOfBoundsException 
	{
		assertEquals(13, Alfabet.parseChar("M"));
	}

	@Test
	public void testString3() throws CharacterOutOfBoundsException 
	{
		assertEquals(18, Alfabet.parseChar("R"));
	}
	
	@Test(expected=CharacterOutOfBoundsException.class)
	public void testString4() throws CharacterOutOfBoundsException 
	{
		assertEquals(0, Alfabet.parseChar("AAAAAAA"));
	}
	
	@Test
	public void testString5() throws CharacterOutOfBoundsException 
	{
		assertEquals(7, Alfabet.parseChar("g"));
	}
	
	@Test
	public void testString6() throws CharacterOutOfBoundsException 
	{
		assertEquals(13, Alfabet.parseChar("m"));
	}

	@Test
	public void testString7() throws CharacterOutOfBoundsException 
	{
		assertEquals(18, Alfabet.parseChar("r"));
	}
	
	@Test(expected=CharacterOutOfBoundsException.class)
	public void testString8() throws CharacterOutOfBoundsException 
	{
		assertEquals(0, Alfabet.parseChar("zzzzzzzzz"));
	}
	
	@Test
	public void testString9() throws CharacterOutOfBoundsException 
	{
		assertEquals(27, Alfabet.parseChar("AA"));
	}
	
	@Test
	public void testString10() throws CharacterOutOfBoundsException 
	{
		assertEquals(648, Alfabet.parseChar("XX"));
	}
}
