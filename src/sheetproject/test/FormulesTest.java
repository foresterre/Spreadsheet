package sheetproject.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class FormulesTest {

	@Test
	public void testPlus() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=6 + 9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus2() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=6+9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus3() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=6	+	9", sheet);
		assertEquals("15", i);
	}
	
	@Test
	public void testMinus() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=20 - 9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus2() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=20-9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus3() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=20	-	9", sheet);
		assertEquals("11", i);
	}
	
	@Test
	public void testTimes() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=4 * 5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes2() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=4*5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes3() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=4	*	5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testProduct() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = Formules.parseFormula("=PRODUCT(A1,A2)", sheet);
		assertEquals("24", i);
	}
	
	@Test
	public void testDivide() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=100 / 5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide2() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=100/5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide3() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=100	/	5", sheet);
		assertEquals("20", i);
	}
	
	@Test
	public void testPower() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=2 ^ 3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower2() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=2^3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower3() {
		Sheet sheet = new Sheet();
		String i = Formules.parseFormula("=2	^	3", sheet);
		assertEquals("8", i);
	}
	
	@Test
	public void testPower4() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = Formules.parseFormula("=POWER(A1,A2)", sheet);
		assertEquals("1296", i);
	}
	
	@Test
	public void testSqrt() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		sheet.setCell(a1, 1, 1);
		String i = Formules.parseFormula("=SQRT(A1)", sheet);
		assertEquals("36", i);
	}
	
	@Test
	public void testMin() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		Cell a3 = new Cell("8");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("4", Formules.parseFormula("=MIN(A1:A3)", sheet));
	}
	
	@Test
	public void testMin2() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("10008");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("34", Formules.parseFormula("=MIN(A1 : A3)", sheet));
	}
	
	@Test
	public void testMax() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		Cell a3 = new Cell("8");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("8", Formules.parseFormula("=MAX( A1:A3 )", sheet));
	}
	
	@Test
	public void testMax2() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("10008");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("10008", Formules.parseFormula("=MAX( A1 : A3 )", sheet));
	}
	
	@Test
	public void testIsNumber() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		sheet.setCell(a1, 1, 1);
		assertEquals("TRUE", Formules.parseFormula("=ISNUMBER( A1 )", sheet));
	}
	
	@Test
	public void testIsNotNumber() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("A");
		sheet.setCell(a1, 1, 1);
		assertEquals("FALSE", Formules.parseFormula("=ISNUMBER( A1 )", sheet));
	}
	
	@Test
	public void testIsEven() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		sheet.setCell(a1, 1, 1);
		assertEquals("TRUE", Formules.parseFormula("=ISEVEN( A1 )", sheet));
	}
	
	@Test
	public void testIsNotEven() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63");
		sheet.setCell(a1, 1, 1);
		assertEquals("FALSE", Formules.parseFormula("=ISEVEN( A1 )", sheet));
	}
	
	@Test
	public void testAverage() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("22");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("40", Formules.parseFormula("=AVERAGE( A1 : A3 )", sheet));
	}
	
	@Test
	public void testRoundUp() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63.3");
		sheet.setCell(a1, 1, 1);
		assertEquals("64.0", Formules.parseFormula("=ROUNDUP( A1 )", sheet));
	}
	
	@Test
	public void testRoundDown() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63.7");
		sheet.setCell(a1, 1, 1);
		assertEquals("63.0", Formules.parseFormula("=ROUNDDOWN( A1 )", sheet));
	}
	
	@Test
	public void testSign() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("63");
		sheet.setCell(a1, 1, 1);
		assertEquals("1", Formules.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testSign2() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("0");
		sheet.setCell(a1, 1, 1);
		assertEquals("0", Formules.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testSign3() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("-76");
		sheet.setCell(a1, 1, 1);
		assertEquals("-1", Formules.parseFormula("=SIGN( A1 )", sheet));
	}
	
	@Test
	public void testMod() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("6");
		Cell a2 = new Cell("4");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		String i = Formules.parseFormula("=MOD(A1,A2)", sheet);
		assertEquals("2.0", i);
	}
	
	@Test
	public void testMedian() {
		Sheet sheet = new Sheet();
		Cell a1 = new Cell("64");
		Cell a2 = new Cell("34");
		Cell a3 = new Cell("22");
		sheet.setCell(a1, 1, 1);
		sheet.setCell(a2, 1, 2);
		sheet.setCell(a3, 1, 3);
		assertEquals("34", Formules.parseFormula("=MEDIAN( A1 : A3 )", sheet));
	}

}
