package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.Median;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class MedianTest {
	Sheet data = null;
	
	@Before
	public void setUp() throws IndexOutOfBoundsException, NullObjectException{
		data = new Sheet();
		data.setCell(new Cell("5"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("3"), 1, 3);
		data.setCell(new Cell("7"), 2, 1);
		data.setCell(new Cell("8"), 2, 2);
		data.setCell(new Cell("1"), 2, 3);
		data.setCell(new Cell("zes"), 3, 1);
		data.setCell(new Cell("zeven"), 3, 2);
		data.setCell(new Cell("acht"), 3, 3);
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertNotNull(new Median());		
	}
	
	@Test
	public void testEvaluatePositiveInt() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4,7)", data), "5.5");		
	}
	
	@Test
	public void testEvaluatePositiveDouble() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4.2,6.8)", data), "5.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(-4,7)", data), "1.5");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4, MEDIAN(4,5))", data), "4.25");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(4, A1)", data), "5.5");		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Median.evaluate("=MEDIAN(A1:A3)", data), "4.0");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Median.evaluate("=MEDIAN(A1:B3))", data), "4.5");
	}	
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Median.evaluate("=MEDIAN(A1:C3)", data), "4.5");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(B1:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(A2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(B2:A1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(4, A1)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(A1, 4)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Median.evaluate("=MEDIAN()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Median.evaluate("=MEDIAN(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Median.evaluate("=MEDIAN(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException 
	{
		assertEquals(Median.evaluate("=MEDIAN(vier, 5)", data), "");
	}

}
