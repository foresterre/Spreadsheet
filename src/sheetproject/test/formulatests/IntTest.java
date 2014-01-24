package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Int;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class IntTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Int());		
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Int.evaluate("=INT(4)", data), "4");		
	}
	
	@Test
	public void testEvaluatePositiveRound() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Int.evaluate("=INT(4.8)", data), "4");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("16.6"), 1, 1);
		assertEquals(Int.evaluate("=INT(A1)", data), "16");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Int.evaluate("=INT(INT(16.7))", data), "16");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Int.evaluate("=INT(A1)", data), "0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Int.evaluate("=INT()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Int.evaluate("=INT(4,5)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Int.evaluate("=INT(vier)", data), "");
	}

}
