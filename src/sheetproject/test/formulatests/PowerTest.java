package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Power;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class PowerTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(2, 4)", data), "16.0");		
	}
	
	@Test
	public void testEvaluatePositiveZeroExp() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(2, 0)", data), "1.0");		
	}
	
	@Test
	public void testEvaluatePositiveZeroBase() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(0, 4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositiveZeroBoth() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(0, 0)", data), "1.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegativeBase() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(-2, 3)", data), "-8.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegativeExp() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Power.evaluate("=POWER(2, -3)", data), "0.125");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Power.evaluate("=POWER(2, A1)", data), "16.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		assertEquals(Power.evaluate("=POWER(A1, 4)", data), "16.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Power.evaluate("=POWER(2, POWER(2, 3))", data), "256.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("vier"), 1, 1);
		assertEquals(Power.evaluate("=POWER(2, A1)", data), "1.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("twee"), 1, 1);
		assertEquals(Power.evaluate("=POWER(A1, 4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Power.evaluate("=POWER()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Power.evaluate("=POWER(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Power.evaluate("=POWER(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Power.evaluate("=POWER(vier)", data), "");
	}

}
