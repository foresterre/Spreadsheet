package sheetproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.exception.CharacterOutOfBoundsException;;


public class AlphabetTest {

	@Test
	public void testConstructor() throws NumberOutOfBoundsException 
	{
		assertNotNull(new Alphabet());
	}
	
	@Test
	public void testInt() throws NumberOutOfBoundsException 
	{
		assertEquals("A", Alphabet.parseInt(1));
	}
	
	@Test
	public void testInt2() throws NumberOutOfBoundsException 
	{
		assertEquals("J", Alphabet.parseInt(10));
	}
	
	@Test
	public void testInt3() throws NumberOutOfBoundsException 
	{
		assertEquals("Z", Alphabet.parseInt(26));
	}
	
	@Test(expected=NumberOutOfBoundsException.class)
	public void testInt4() throws NumberOutOfBoundsException 
	{
		assertEquals("", Alphabet.parseInt(100000));
	}
	
	@Test(expected=NumberOutOfBoundsException.class)
	public void testInt5() throws NumberOutOfBoundsException 
	{
		assertEquals("", Alphabet.parseInt(0));
	}
	
	@Test
	public void testInt6() throws NumberOutOfBoundsException 
	{
		assertEquals("AA", Alphabet.parseInt(27));
	}
	
	@Test
	public void testInt7() throws NumberOutOfBoundsException 
	{
		assertEquals("XX", Alphabet.parseInt(648));
	}
	
	@Test
	public void testString() throws CharacterOutOfBoundsException 
	{
		assertEquals(7, Alphabet.parseChar("G"));
	}
	
	@Test
	public void testString2() throws CharacterOutOfBoundsException 
	{
		assertEquals(13, Alphabet.parseChar("M"));
	}

	@Test
	public void testString3() throws CharacterOutOfBoundsException 
	{
		assertEquals(18, Alphabet.parseChar("R"));
	}
	
	@Test(expected=CharacterOutOfBoundsException.class)
	public void testString4() throws CharacterOutOfBoundsException 
	{
		assertEquals(0, Alphabet.parseChar("AAAAAAA"));
	}
	
	@Test
	public void testString5() throws CharacterOutOfBoundsException 
	{
		assertEquals(7, Alphabet.parseChar("g"));
	}
	
	@Test
	public void testString6() throws CharacterOutOfBoundsException 
	{
		assertEquals(13, Alphabet.parseChar("m"));
	}

	@Test
	public void testString7() throws CharacterOutOfBoundsException 
	{
		assertEquals(18, Alphabet.parseChar("r"));
	}
	
	@Test(expected=CharacterOutOfBoundsException.class)
	public void testString8() throws CharacterOutOfBoundsException 
	{
		assertEquals(0, Alphabet.parseChar("zzzzzzzzz"));
	}
	
	@Test
	public void testString9() throws CharacterOutOfBoundsException 
	{
		assertEquals(27, Alphabet.parseChar("AA"));
	}
	
	@Test
	public void testString10() throws CharacterOutOfBoundsException 
	{
		assertEquals(648, Alphabet.parseChar("XX"));
	}
}
