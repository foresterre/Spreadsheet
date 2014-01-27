package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Parser;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class ParserTest {
	Sheet data = null;
	
	@Before
	public void setUp()
	{		
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Parser());		
	}
	
	@Test
	public void testParsePositive() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		assertEquals(Parser.parse("=SUM(1,2)", data), "3.0");
	}
	
	@Test
	public void testParsePositiveNested() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		assertEquals(Parser.parse("=SUM(1, PRODUCT(2, 4))", data), "9.0");
	}
	
	@Test
	public void testParseNegativeNotStartingIs() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		assertEquals(Parser.parse("SUM(1, 2)", data), "SUM(1, 2)");
	}
	
	@Test(expected=IllegalFormulaException.class)
	public void testParseNegativeNotValidFormula() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		Parser.parse("=SYSTEM(1, 2)", data);
	}

	@Test
	public void testEvaluatePositiveFormula() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		assertEquals(Parser.evaluate("SUM(1,2)", data), "3.0");
	}
	
	@Test
	public void testEvaluatePositiveCell() throws IllegalFormulaException, CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException
	{		
		data.setCell(new Cell("7"), 1, 2);
		assertEquals(Parser.evaluate("A2", data), "7");
	}
	
	@Test
	public void testEvaluateNegativeFormula() throws IllegalFormulaException, CharacterOutOfBoundsException
	{		
		assertEquals(Parser.evaluate("SUM()", data), "");
	}
	
	@Test//(expected=NullPointerException.class)
	public void testEvaluateNegativeCellNotExisting() throws IllegalFormulaException, CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException
	{		
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Parser.evaluate("A2", data), "");
	}

	@Test
	public void testContainsFormulaPositive() {
		assertEquals(Parser.containsFormula("Islogical"), true);
	}
	
	@Test
	public void testContainsFormulaNegative() {
		assertEquals(Parser.containsFormula("System"), false);
	}

}
