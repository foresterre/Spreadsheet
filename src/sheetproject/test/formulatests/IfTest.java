package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Isnumber;
import sheetproject.formula.Max;
import sheetproject.formula.If;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class IfTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new If());		
	}
	
	@Test
	public void testEvaluatePositiveEqualTrue() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 = 0, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 = 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveNotEqualTrue() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 != 0, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveNotEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 != 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveLessEqualTrue1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 <= 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveLessEqualTrue2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 <= 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveLessEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 <= -1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveLessTrue() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(-1 < 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveLessFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 < -1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveGreaterTrue() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 > 0, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveGreaterFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 > 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveGreaterEqualTrue1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 >= 0, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveGreaterEqualTrue2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(1 >= 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveGreaterEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 >= 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveCellTrue1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("1"), 1, 1);
		assertEquals(If.evaluate("=IF(A1 = 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveCellTrue2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("1"), 1, 1);
		assertEquals(If.evaluate("=IF(1 = A1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveCellFalse1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		assertEquals(If.evaluate("=IF(A1 < 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveCellFalse2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		assertEquals(If.evaluate("=IF(1 > A1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveTwoCellsTrue() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		data.setCell(new Cell("1"), 1, 2);
		assertEquals(If.evaluate("=IF(A1 > A2, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveTwoCellsFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		data.setCell(new Cell("1"), 1, 2);
		assertEquals(If.evaluate("=IF(A1 < A2, text 1, text 2)", data), "text 2");
	}
	
	@Test
	public void testEvaluatePositiveNestedTrue1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(SUM(1,2) > 1, text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveNestedTrue2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(3.0 = SUM(1,2), text 1, text 2)", data), "text 1");		
	}
	
	@Test
	public void testEvaluatePositiveNestedFalse1() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(SUM(1,2) = 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluatePositiveNestedFalse2() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 >= SUM(1,2), text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF()", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(text1, text2)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(0 = 0, text1, text2, text3)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeNotLogical() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(x = x, text 1, text 2)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeNestedEqualsFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) = 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeNestedNotEqualsFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) != 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeNestedLessEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) <= 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeNestedLessFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) < 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeNestedGreaterFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) > 1, text 1, text 2)", data), "text 2");		
	}
	
	@Test
	public void testEvaluateNegativeNestedGreaterEqualFalse() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(PROPER(text) >= 1, text 1, text 2)", data), "text 2");		
	}	
	
	@Test(expected=IllegalFormulaException.class)
	public void testEvaluateNegativeNestedIllegalFormula() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{			
		assertEquals(If.evaluate("=IF(SYSTEM(1,2) >= 1, text 1, text 2)", data), "text 2");		
	}
}
