import static org.junit.Assert.*;

import org.junit.Test;


public class FormulaTest {

	@Test
	public void testPlus() {
		String i = Formula.parse("=6 + 9");
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus2() {
		String i = Formula.parse("=6+9");
		assertEquals("15", i);
	}
	
	@Test
	public void testPlus3() {
		String i = Formula.parse("=6	+	9");
		assertEquals("15", i);
	}
	
	@Test
	public void testMinus() {
		String i = Formula.parse("=20 - 9");
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus2() {
		String i = Formula.parse("=20-9");
		assertEquals("11", i);
	}
	
	@Test
	public void testMinus3() {
		String i = Formula.parse("=20	-	9");
		assertEquals("11", i);
	}
	
	@Test
	public void testTimes() {
		String i = Formula.parse("=4 * 5");
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes2() {
		String i = Formula.parse("=4*5");
		assertEquals("20", i);
	}
	
	@Test
	public void testTimes3() {
		String i = Formula.parse("=4	*	5");
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide() {
		String i = Formula.parse("=100 / 5");
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide2() {
		String i = Formula.parse("=100/5");
		assertEquals("20", i);
	}
	
	@Test
	public void testDivide3() {
		String i = Formula.parse("=100	/	5");
		assertEquals("20", i);
	}

}
