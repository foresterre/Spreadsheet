package sheetproject.test.formulatests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.formula.Isnumber;
import sheetproject.formula.Median;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class MedianTest {
	Sheet data = null;
	
	@Before
	public void setUp(){
		data = new Sheet();
	}
	
	@Test
	public void testConstructor() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertNotNull(new Median());		
	}
	
	@Test
	public void testEvaluatePositiveInt() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4,7)", data), "5.5");		
	}
	
	@Test
	public void testEvaluatePositiveDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4.2,6.8)", data), "5.5");		
	}
	
	@Test
	public void testEvaluatePositiveNegative() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(-4,7)", data), "1.5");		
	}
	
	@Test
	public void testEvaluatePositiveNested() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{			
		assertEquals(Median.evaluate("=MEDIAN(4, MEDIAN(4,5))", data), "4.25");		
	}
	
	@Test
	public void testEvaluatePositiveCell() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("7"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(4, A1)", data), "5.5");		
	}
	
	@Test
	public void testEvaluateNegativeCell1() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(4, A1)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeCell2() throws CharacterOutOfBoundsException, IllegalFormulaException, IndexOutOfBoundsException, NullObjectException 
	{			
		data.setCell(new Cell("zeven"), 1, 1);
		assertEquals(Median.evaluate("=MEDIAN(A1, 4)", data), "2.0");		
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchEmpty() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Median.evaluate("=MEDIAN()", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchLessArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Median.evaluate("=MEDIAN(4)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoPatternMatchMoreArguments() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Median.evaluate("=MEDIAN(4,5,6)", data), "");
	}
	
	@Test
	public void testEvaluateNegativeNoDouble() throws CharacterOutOfBoundsException, IllegalFormulaException 
	{
		assertEquals(Median.evaluate("=MEDIAN(vier, 5)", data), "");
	}

}
