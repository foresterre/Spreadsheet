package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Sqrt extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern sqrt = Pattern.compile("\\s*SQRT\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m12 = sqrt.matcher(res);
		if (m12.find())
		{
			int i = Alfabet.parseChar(m12.group(1));
			int j = Integer.parseInt(m12.group(2));
			try
			{
				int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
				res = Integer.toString(res2 * res2);
			}
			catch (Exception e)
			{
				res = "NOT A NUMBER";
			}
		}
		
		return res;
	
	}

}
