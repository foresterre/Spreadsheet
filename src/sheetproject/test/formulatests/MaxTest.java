package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Isnumber;
import sheetproject.formula.Max;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class MaxTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Max());		
	}
	
	@Test
	public void testEvaluatePositiveInt1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(4,7)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveInt2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(7,4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveDouble1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(7, 4.5)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveDouble2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(7.5, 4)", data), "7.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(-7, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(7, -4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Max.evaluate("=MAX(4, MAX(4,5))", data), "5.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(4, A1)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, 4)", data), "7.0");		
	}
	
	@Test
	public void testEvaluatePositiveCellSame() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, A1)", data), "7.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Max.evaluate("=MAX(4, A1)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Max.evaluate("=MAX(A1, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Max.evaluate("=MAX()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Max.evaluate("=MAX(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Max.evaluate("=MAX(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Max.evaluate("=MAX(vier, 5)", data), "");
	}

}
