package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Roundup;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class RoundupTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Roundup());		
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(4, 0)", data), "4.0");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(4.2, 0)", data), "5.0");		
	}
	
	@Test
	public void testEvaluatePositive3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(4.13, 1)", data), "4.2");		
	}
	
	@Test
	public void testEvaluatePositive4() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(4.173, 2)", data), "4.18");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(-4, 0)", data), "-4.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(-4.5, 0)", data), "-5.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegativeDigit() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(14, -1)", data), "20.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Roundup.evaluate("=ROUNDUP(4.874, COUNT(1,2))", data), "4.88");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("0"), 1, 1);
		assertEquals(Roundup.evaluate("=ROUNDUP(4.5, A1)", data), "5.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Roundup.evaluate("=ROUNDUP(4, A1)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Roundup.evaluate("=ROUNDUP(A1, 4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Roundup.evaluate("=ROUNDUP()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Roundup.evaluate("=ROUNDUP(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Roundup.evaluate("=ROUNDUP(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Roundup.evaluate("=ROUNDUP(vier, 5)", data), "");
	}

}
