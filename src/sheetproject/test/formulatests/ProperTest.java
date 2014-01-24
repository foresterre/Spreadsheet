package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Proper;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class ProperTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Proper());		
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Proper.evaluate("=PROPER(\"TEXT\")", data), "Text");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Proper.evaluate("=PROPER(\"text\")", data), "Text");		
	}
	
	@Test
	public void testEvaluatePositiveCharacter() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Proper.evaluate("=PROPER(\"t\")", data), "T");		
	}
	
	@Test
	public void testEvaluatePositiveSentence() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Proper.evaluate("=PROPER(\"This IS a teXt\")", data), "This Is A Text");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TEXT"), 1, 1);
		assertEquals(Proper.evaluate("=PROPER(A1)", data), "Text");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Proper.evaluate("=PROPER(ISLOGICAL(16))", data), "False");		
	}
	
	@Test
	public void testEvaluatePositiveNestedText() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Proper.evaluate("=PROPER(\"ISLOGICAL(16)\")", data), "Islogical(16)");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Proper.evaluate("=PROPER()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Proper.evaluate("=PROPER(A1,A1)", data), "");
	}

}
