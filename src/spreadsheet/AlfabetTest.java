import static org.junit.Assert.*;

import org.junit.Test;


public class AlfabetTest {

	@Test
	public void testInt() {
		assertEquals("A",Alfabet.parseInt(1));
	}
	
	@Test
	public void testInt2() {
		assertEquals("J",Alfabet.parseInt(10));
	}
	
	@Test
	public void testInt3() {
		assertEquals("Z",Alfabet.parseInt(26));
	}
	
	@Test
	public void testString() {
		assertEquals(7,Alfabet.parseChar("G"));
	}
	
	@Test
	public void testString2() {
		assertEquals(13,Alfabet.parseChar("M"));
	}

	@Test
	public void testString3() {
		assertEquals(18,Alfabet.parseChar("R"));
	}
	
	@Test
	public void testString4() {
		assertEquals(7,Alfabet.parseChar("g"));
	}
	
	@Test
	public void testString5() {
		assertEquals(13,Alfabet.parseChar("m"));
	}

	@Test
	public void testString6() {
		assertEquals(18,Alfabet.parseChar("r"));
	}
}
