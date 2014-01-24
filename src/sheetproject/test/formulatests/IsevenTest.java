package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Iseven;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class IsevenTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Iseven());		
	}
	
	@Test
	public void testEvaluatePositiveEven() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(4)", data), "TRUE");		
	}	
	
	@Test
	public void testEvaluatePositiveOdd() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(3)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositiveNegativeEven() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(-4)", data), "TRUE");		
	}	
	
	@Test
	public void testEvaluatePositiveNegativeOdd() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(-3)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositiveDoubleEven() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(4.3)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveDoubleOdd() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Iseven.evaluate("=ISEVEN(3.3)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Iseven.evaluate("=ISEVEN(A1)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Iseven.evaluate("=ISEVEN(ISEVEN(16))", data), "NOT A NUMBER");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Iseven.evaluate("=ISEVEN(A1)", data), "NOT A NUMBER");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Iseven.evaluate("=ISEVEN()", data), "NOT A NUMBER");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Iseven.evaluate("=ISEVEN(4,5)", data), "NOT A NUMBER");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Iseven.evaluate("=ISEVEN(vier)", data), "NOT A NUMBER");
	}

}
