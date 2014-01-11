package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Max;
import sheetproject.formula.Min;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class MinTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositiveInt1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(4,7)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveInt2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(7,4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveDouble1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(7, 4.5)", data), "4.5");		
	}
	
	@Test
	public void testEvaluatePositiveDouble2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(7.5, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(-7, 4)", data), "-7.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(7, -4)", data), "-4.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Min.evaluate("=MIN(5, MIN(4,5))", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Min.evaluate("=MIN(7, A1)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Min.evaluate("=MIN(A1, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveCellSame() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Min.evaluate("=MIN(A1, A1)", data), "7.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Min.evaluate("=MIN(4, A1)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Min.evaluate("=MIN(A1, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Min.evaluate("=MIN()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Min.evaluate("=MIN(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Min.evaluate("=MIN(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Min.evaluate("=MIN(vier, 5)", data), "");
	}

}
