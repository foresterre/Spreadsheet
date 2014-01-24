package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.Average;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class AverageTest {
	Sheet data = null;
	
	@Before
	public void setUp() throws IndexOutOfBoundsException, NullObjectException{
		data = new Sheet();
		data.setCell(new Cell("3"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("5"), 1, 3);
		data.setCell(new Cell("6"), 2, 1);
		data.setCell(new Cell("7"), 2, 2);
		data.setCell(new Cell("8"), 2, 3);
		data.setCell(new Cell("zes"), 3, 1);
		data.setCell(new Cell("zeven"), 3, 2);
		data.setCell(new Cell("acht"), 3, 3);
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Average());		
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Average.evaluate("=AVERAGE(4,7)", data), "5.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{			
		assertEquals(Average.evaluate("=AVERAGE(-4,7)", data), "1.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{			
		assertEquals(Average.evaluate("=AVERAGE(4,-7.5)", data), "-1.75");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{			
		assertEquals(Average.evaluate("=AVERAGE(4, AVERAGE(4,5))", data), "4.25");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException  
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Average.evaluate("=AVERAGE(4, A1)", data), "5.5");		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Average.evaluate("=AVERAGE(A1:A3)", data), "4.0");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Average.evaluate("=AVERAGE(A1:B3))", data), "5.5");
	}	
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Average.evaluate("=AVERAGE(A1:C3)", data), "5.5");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Average.evaluate("=AVERAGE(B1:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Average.evaluate("=AVERAGE(A2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Average.evaluate("=AVERAGE(B2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException  
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Average.evaluate("=AVERAGE(4, A1)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException, NumberOutOfBoundsException  
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Average.evaluate("=AVERAGE(A1, 4)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{
		assertEquals(Average.evaluate("=AVERAGE()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{
		assertEquals(Average.evaluate("=AVERAGE(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{
		assertEquals(Average.evaluate("=AVERAGE(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException  
	{
		assertEquals(Average.evaluate("=AVERAGE(vier, 5)", data), "");
	}

}
