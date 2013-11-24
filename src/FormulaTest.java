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

}
