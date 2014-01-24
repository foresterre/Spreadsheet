package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.Count;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class CountTest {
	Sheet data = null;
	
	@Before
	public void setUp() throws IndexOutOfBoundsException, NullObjectException{
		data = new Sheet();
		data.setCell(new Cell("5"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("3"), 1, 3);
		data.setCell(new Cell(""), 2, 1);
		data.setCell(new Cell("8"), 2, 2);
		data.setCell(new Cell("2"), 2, 3);
		data.setCell(new Cell("-7"), 3, 1);
		data.setCell(new Cell("zeven"), 3, 2);
		data.setCell(new Cell("acht"), 3, 3);
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Count());		
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{			
		assertEquals(Count.evaluate("=COUNT(4,7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{			
		assertEquals(Count.evaluate("=COUNT(-4,7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{			
		assertEquals(Count.evaluate("=COUNT(4,-7)", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{			
		assertEquals(Count.evaluate("=COUNT(4, COUNT(4,5))", data), "2");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException, ScriptException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(4, A1)", data), "2");		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Count.evaluate("=COUNT(A1:A3)", data), "3");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Count.evaluate("=COUNT(A1:B3))", data), "5");
	}	
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Count.evaluate("=COUNT(A1:C3)", data), "6");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Count.evaluate("=COUNT(B1:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Count.evaluate("=COUNT(A2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Count.evaluate("=COUNT(B2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException, ScriptException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(4, A1)", data), "1");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException, ScriptException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Count.evaluate("=COUNT(A1, A1)", data), "0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{
		assertEquals(Count.evaluate("=COUNT()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{
		assertEquals(Count.evaluate("=COUNT(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, ScriptException 
	{
		assertEquals(Count.evaluate("=COUNT(4,5,6)", data), "");
	}

}
