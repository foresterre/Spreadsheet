package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Isnumber;
import sheetproject.formula.Product;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class ProductTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Product());		
	}
	
	@Test
	public void testEvaluatePositive() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Product.evaluate("=PRODUCT(2, 4)", data), "8.0");		
	}
	
	@Test
	public void testEvaluatePositiveZero1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Product.evaluate("=PRODUCT(2, 0)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositiveZero2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Product.evaluate("=PRODUCT(0, 4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative1() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Product.evaluate("=PRODUCT(-2, 4)", data), "-8.0");		
	}
	
	@Test
	public void testEvaluatePositiveNegative2() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{		
		assertEquals(Product.evaluate("=PRODUCT(2, -4)", data), "-8.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("4"), 1, 1);
		assertEquals(Product.evaluate("=PRODUCT(2, A1)", data), "8.0");		
	}
	
	@Test
	public void testEvaluatePositiveCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("2"), 1, 1);
		assertEquals(Product.evaluate("=PRODUCT(A1, 4)", data), "8.0");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Product.evaluate("=PRODUCT(2, PRODUCT(2, 3))", data), "12.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("vier"), 1, 1);
		assertEquals(Product.evaluate("=PRODUCT(2, A1)", data), "0.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("twee"), 1, 1);
		assertEquals(Product.evaluate("=PRODUCT(A1, 4)", data), "0.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Product.evaluate("=PRODUCT()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Product.evaluate("=PRODUCT(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Product.evaluate("=PRODUCT(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Product.evaluate("=PRODUCT(vier)", data), "");
	}

}
