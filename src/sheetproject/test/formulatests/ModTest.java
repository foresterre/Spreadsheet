package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Mod;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class ModTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositive1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(7,4)", data), "3.0");		
	}
	
	@Test
	public void testEvaluatePositive2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(8,4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositive3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(9,4)", data), "1.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(6,-3)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(-7,3)", data), "2.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative3() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(7,-3)", data), "-2.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative4() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(-7,-3)", data), "-1.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(7, MOD(7,5))", data), "1.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Mod.evaluate("=MOD(7, A1)", data), "3.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Mod.evaluate("=MOD(A1, 4)", data), "3.0");		
	}
	
	@Test
	public void testEvaluatePositiveCellSame() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Mod.evaluate("=MOD(A1, A1)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositiveZero() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(0,5))", data), "0.0");		
	}
	
	@Test
	public void testEvaluateNegativeZero() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Mod.evaluate("=MOD(5,0))", data), "DIVIDING BY ZERO");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Mod.evaluate("=MOD(4, A1)", data), "NOT A NUMBER");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Mod.evaluate("=MOD(A1, 4)", data), "NOT A NUMBER");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Mod.evaluate("=MOD()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Mod.evaluate("=MOD(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Mod.evaluate("=MOD(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Mod.evaluate("=MOD(vier, 5)", data), "");
	}

}
