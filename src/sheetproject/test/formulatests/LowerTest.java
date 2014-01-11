package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Lower;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class LowerTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testEvaluatePositiveText() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Lower.evaluate("=LOWER(TEXT)", data), "text");		
	}
	
	@Test
	public void testEvaluatePositiveCellNumber() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Lower.evaluate("=LOWER(A1)", data), "4");		
	}
	
	@Test
	public void testEvaluatePositiveCellText() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("TEXT"), 1, 1);
		assertEquals(Lower.evaluate("=LOWER(A1)", data), "text");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Lower.evaluate("=LOWER(LOWER(TEXT))", data), "text");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Lower.evaluate("=LOWER()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Lower.evaluate("=LOWER(4,5)", data), "");
	}

}
