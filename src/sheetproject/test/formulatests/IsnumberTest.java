package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Isnumber;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class IsnumberTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Isnumber.evaluate("=ISNUMBER(4)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Isnumber.evaluate("=ISNUMBER(4.1)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Isnumber.evaluate("=ISNUMBER(-4)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Isnumber.evaluate("=ISNUMBER(A1)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Isnumber.evaluate("=ISNUMBER(ISNUMBER(4))", data), "FALSE");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Isnumber.evaluate("=ISNUMBER(A1)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Isnumber.evaluate("=ISNUMBER()", data), "FALSE");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Isnumber.evaluate("=ISNUMBER(4,5)", data), "FALSE");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Isnumber.evaluate("=ISNUMBER(vier)", data), "FALSE");
	}

}
