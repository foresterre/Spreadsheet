package sheetproject.formula;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Count extends AbstractFormula 
{

	@Override
	public String parse(String formula) 
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	// TO be determined
	// count only the cells that are filled
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		return res;
	}
}
