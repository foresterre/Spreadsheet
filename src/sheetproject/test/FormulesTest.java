package sheetproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.FormulesList;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class FormulesTest {

	@Test
	public void testPlus() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=6 + 9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus2() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=6+9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus3() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=6	+	9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testMinus() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=20 - 9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus2() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=20-9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus3() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=20	-	9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testTimes() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=4 * 5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes2() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=4*5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes3() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=4	*	5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testProduct() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = FormulesList.parseFormula("=PRODUCT(A1,A2)", sheet);
		assertEquals("24", i);
	}
	
	@Test
	public void testDivide() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=100 / 5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide2() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=100/5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide3() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=100	/	5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testPower() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=2 ^ 3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower2() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=2^3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower3() throws CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		String i = FormulesList.parseFormula("=2	^	3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower4() throws CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = FormulesList.parseFormula("=POWER(A1,A2)", sheet);
		assertEquals("1296", i);
	}
	
	@Test
	public void testSqrt() throws CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		sheet.setCell(a1, 1, 1);
		String i = FormulesList.parseFormula("=SQRT(A1)", sheet);
		assertEquals("36", i);
	}
	
	@Test
	public void testMin() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		Cell a3 = new Cell("8");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("4", FormulesList.parseFormula("=MIN(A1:A3)", sheet));
	}
	
	@Test
	public void testMin2() throws CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("10008");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("34", FormulesList.parseFormula("=MIN(A1 : A3)", sheet));
	}
	
	@Test
	public void testMax() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		Cell a3 = new Cell("8");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("8", FormulesList.parseFormula("=MAX( A1:A3 )", sheet));
	}
	
	@Test
	public void testMax2() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("10008");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("10008", FormulesList.parseFormula("=MAX( A1 : A3 )", sheet));
	}
	
	@Test
	public void testIsNumber() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		sheet.setCell(a1, 1, 1);
		assertEquals("TRUE", FormulesList.parseFormula("=ISNUMBER( A1 )", sheet));
	}
	
	@Test
	public void testIsNotNumber() throws CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("A");
		sheet.setCell(a1, 1, 1);
		assertEquals("FALSE", FormulesList.parseFormula("=ISNUMBER( A1 )", sheet));
	}
	
	@Test
	public void testIsEven() throws CharacterOutOfBoundsException, IndexOutOfBoundsException, NullObjectException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		sheet.setCell(a1, 1, 1);
		assertEquals("TRUE", FormulesList.parseFormula("=ISEVEN( A1 )", sheet));
	}
	
	@Test
	public void testIsNotEven() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63");
		sheet.setCell(a1, 1, 1);
		assertEquals("FALSE", FormulesList.parseFormula("=ISEVEN( A1 )", sheet));
	}
	
	@Test
	public void testAverage() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("22");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("40", FormulesList.parseFormula("=AVERAGE( A1 : A3 )", sheet));
	}
	
	@Test
	public void testRoundUp() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63.3");
		sheet.setCell(a1, 1, 1);
		assertEquals("64.0", FormulesList.parseFormula("=ROUNDUP( A1 )", sheet));
	}
	
	@Test
	public void testRoundDown() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63.7");
		sheet.setCell(a1, 1, 1);
		assertEquals("63.0", FormulesList.parseFormula("=ROUNDDOWN( A1 )", sheet));
	}
	
	@Test
	public void testSign() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63");
		sheet.setCell(a1, 1, 1);
		assertEquals("1", FormulesList.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testSign2() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("0");
		sheet.setCell(a1, 1, 1);
		assertEquals("0", FormulesList.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testSign3() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("-76");
		sheet.setCell(a1, 1, 1);
		assertEquals("-1", FormulesList.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testMod() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = FormulesList.parseFormula("=MOD(A1,A2)", sheet);
		assertEquals("2.0", i);
	}
	
	@Test
	public void testMedian() throws IndexOutOfBoundsException, NullObjectException, CharacterOutOfBoundsException {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("22");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("34", FormulesList.parseFormula("=MEDIAN( A1 : A3 )", sheet));
	}

}
