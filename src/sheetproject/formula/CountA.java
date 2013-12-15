package sheetproject.formula;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class CountA extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	// TO be determined
	// count all cells
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		return res;
	}

}
