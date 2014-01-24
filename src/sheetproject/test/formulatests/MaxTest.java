package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.Max;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class MaxTest {
	Sheet data = null;
	
	@Before
	public void setUp() throws IndexOutOfBoundsException, NullObjectException{
		data = new Sheet();
		data.setCell(new Cell("5"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("3"), 1, 3);
		data.setCell(new Cell("7"), 2, 1);
		data.setCell(new Cell("8"), 2, 2);
		data.setCell(new Cell("2"), 2, 3);
		data.setCell(new Cell("zes"), 3, 1);
		data.setCell(new Cell("zeven"), 3, 2);
		data.setCell(new Cell("acht"), 3, 3);
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertNotNull(new Max());		
	}
	
	@Test
	public void testEvaluatePositiveInt1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(4,7)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveInt2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(7,4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveDouble1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(7, 4.5)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveDouble2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(7.5, 4)", data), "7.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(-7, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(7, -4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(4, MAX(4,5))", data), "5.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(4, A1)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, 4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveCellSame() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, A1)", data), "7.0");		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Max.evaluate("=MAX(A1:A3)", data), "5.0");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Max.evaluate("=MAX(A1:B3))", data), "8.0");
	}	
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Max.evaluate("=MAX(A1:C3)", data), "8.0");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(B1:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(A2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Max.evaluate("=MAX(B2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Max.evaluate("=MAX(4, A1)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Max.evaluate("=MAX()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Max.evaluate("=MAX(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Max.evaluate("=MAX(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Max.evaluate("=MAX(vier, 5)", data), "");
	}

}
