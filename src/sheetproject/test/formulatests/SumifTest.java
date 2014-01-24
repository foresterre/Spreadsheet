package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.formula.Sumif;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class SumifTest {
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
		assertNotNull(new Sumif());		
	}
	
	@Test
	public void testPositiveOneColumn() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Sumif.evaluate("=SUMIF(A1:A3, >2)", data), "12.0");
	}
	
	@Test
	public void testPositiveTwoColumns() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Sumif.evaluate("=SUMIF(A1:B3, >2)", data), "33.0");
	}
	
	@Test
	public void testPositiveNotAllCellsMatch() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Sumif.evaluate("=SUMIF(A1:B3, >5)", data), "21.0");
	}
	
	@Test
	public void testPositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {	
		assertEquals(Sumif.evaluate("=SUMIF(A1:C3, >5)", data), "21.0");
	}
	
	@Test
	public void testEvaluateNegativeColumnMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF(B1:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeRowMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF(A2:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeBothMore() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF(B2:A1, >5)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF()", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF(text1)", data), "");		
	}
	
	@Test
	public void testEvaluateNegativeMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException 
	{			
		assertEquals(Sumif.evaluate("=SUMIF(0 = 0, text1, text2)", data), "");		
	}

}
