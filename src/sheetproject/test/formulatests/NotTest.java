package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Not;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class NotTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Not.evaluate("=NOT(4)", data), "NOT A LOGICAL VALUE");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TRUE"), 1, 1);
		assertEquals(Not.evaluate("=NOT(A1)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Not.evaluate("=NOT(NOT(TRUE))", data), "TRUE");		
	}
	
	@Test
	public void testEvaluateNegativeNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Not.evaluate("=NOT(NOT(FALSE))", data), "FALSE");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Not.evaluate("=NOT(A1)", data), "NOT A LOGICAL VALUE");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Not.evaluate("=NOT()", data), "NOT A LOGICAL VALUE");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Not.evaluate("=NOT(4,5)", data), "NOT A LOGICAL VALUE");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Not.evaluate("=NOT(vier)", data), "NOT A LOGICAL VALUE");
	}

}
