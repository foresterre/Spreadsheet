package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Or;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class OrTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Or());		
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Or.evaluate("=OR(false,false)", data), "FALSE");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Or.evaluate("=OR(true,false)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Or.evaluate("=OR(false,true)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositive4() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Or.evaluate("=OR(true,true)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TRUE"), 1, 1);
		assertEquals(Or.evaluate("=OR(A1, false)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TRUE"), 1, 1);
		assertEquals(Or.evaluate("=OR(false, A1)", data), "TRUE");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Or.evaluate("=OR(false, OR(TRUE, true))", data), "TRUE");		
	}
	
	@Test
	public void testEvaluateNegative() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Or.evaluate("=OR(3, false)", data), "NOT A LOGICAL VALUE");		
	}
	
	@Test
	public void testEvaluateNegativeCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zestien"), 1, 1);
		assertEquals(Or.evaluate("=OR(A1, false)", data), "NOT A LOGICAL VALUE");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Or.evaluate("=OR()", data), "NOT A LOGICAL VALUE");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Or.evaluate("=OR(4,5)", data), "NOT A LOGICAL VALUE");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Or.evaluate("=OR(vier)", data), "NOT A LOGICAL VALUE");
	}

}
