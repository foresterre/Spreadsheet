package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.If;
import sheetproject.formula.Max;
import sheetproject.formula.Countif;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class CountifTest {
	Sheet data = null;
	
	@Before
	public void setUp() throws IndexOutOfBoundsException, NullObjectException{
		data = new Sheet();
		data.setCell(new Cell("3"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("5"), 1, 3);
		data.setCell(new Cell("6"), 2, 1);
		data.setCell(new Cell("7"), 2, 2);
		data.setCell(new Cell("8"), 2, 3);
		data.setCell(new Cell("zes"), 3, 1);
		data.setCell(new Cell("zeven"), 3, 2);
		data.setCell(new Cell("acht"), 3, 3);
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Countif());		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Countif.evaluate("=COUNTIF(A1:A3, >2))", data), "3");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Countif.evaluate("=COUNTIF(A1:B3, >2))", data), "6");
	}
	
	@Test
	public void testPositiveNotAllCellsMatch() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Countif.evaluate("=COUNTIF(A1:B3, >5))", data), "3");
	}
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Countif.evaluate("=COUNTIF(A1:C3, >5))", data), "3");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF(B1:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF(A2:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF(B2:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF()", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF(text1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Countif.evaluate("=COUNTIF(0 = 0, text1, text2)", data), "");		
	}

}
