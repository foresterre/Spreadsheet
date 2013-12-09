import static org.junit.Assert.*;

import org.junit.Test;


public class SheetTest {

	@Test
	public void testGetColumns() {
		assertEquals(10, Sheet.getColumns());
	}

	@Test
	public void testGetRows() {
		assertEquals(20, Sheet.getRows());
	}

	@Test
	public void testGetCell() {
		Sheet a = new Sheet();
		Cell b = new Cell("test");
		a.setCell(b, 1, 1);
		
		assertTrue(a.getCell(1, 1).equals(b));
	}

	@Test
	public void testSetCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
