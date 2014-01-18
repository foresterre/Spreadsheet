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
import sheetproject.formula.Sumif;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class SumifTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	@Test
	public void testPositive() throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		data.setCell(new Cell("3"), 1, 1);
		data.setCell(new Cell("4"), 1, 2);
		data.setCell(new Cell("5"), 1, 3);
		data.setCell(new Cell("6"), 1, 4);
		data.setCell(new Cell("7"), 1, 5);
		data.setCell(new Cell("8"), 1, 6);
		assertEquals(Sumif.evaluate("=SUMIF(A1:A6, >2))", data), "33.0");
	}

}
