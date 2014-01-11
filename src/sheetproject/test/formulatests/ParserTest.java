package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.formula.Parser;
import sheetproject.spreadsheet.Sheet;

public class ParserTest {

	@Test
	public void testParse() {
		Sheet data = new Sheet();
		try {
			System.out.println(Parser.parse("=SUM(1,2)", data));
		} catch (IllegalFormulaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CharacterOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testEvaluate() {
		
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
