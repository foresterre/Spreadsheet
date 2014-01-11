package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Islogical;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class IslogicalTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Islogical.evaluate("=ISLOGICAL(TRUE)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Islogical.evaluate("=ISLOGICAL(FALSE)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Islogical.evaluate("=ISLOGICAL(4)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TRUE"), 1, 1);
		assertEquals(Islogical.evaluate("=ISLOGICAL(A1)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Islogical.evaluate("=ISLOGICAL(ISLOGICAL(TRUE))", data), "TRUE");		
	}
	
	@Test
	public void testEvaluateNegativeNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Islogical.evaluate("=ISLOGICAL(ISLOGICAL(FALSE))", data), "TRUE");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Islogical.evaluate("=ISLOGICAL(A1)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Islogical.evaluate("=ISLOGICAL()", data), "FALSE");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Islogical.evaluate("=ISLOGICAL(4,5)", data), "FALSE");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Islogical.evaluate("=ISLOGICAL(vier)", data), "FALSE");
	}

}
