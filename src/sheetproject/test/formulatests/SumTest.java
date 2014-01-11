package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Sum;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class SumTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Sum());		
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Sum.evaluate("=SUM(2, 4)", data), "6.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Sum.evaluate("=SUM(-2, 4)", data), "2.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Sum.evaluate("=SUM(2, -4)", data), "-2.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Sum.evaluate("=SUM(2, A1)", data), "6.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		assertEquals(Sum.evaluate("=SUM(A1, 4)", data), "6.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Sum.evaluate("=SUM(2, SUM(2, 3))", data), "7.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("vier"), 1, 1);
		assertEquals(Sum.evaluate("=SUM(2, A1)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("twee"), 1, 1);
		assertEquals(Sum.evaluate("=SUM(A1, 4)", data), "4.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Sum.evaluate("=SUM()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Sum.evaluate("=SUM(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Sum.evaluate("=SUM(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Sum.evaluate("=SUM(vier)", data), "");
	}

}
