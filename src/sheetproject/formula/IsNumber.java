package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class IsNumber extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern isNumber = Pattern.compile("\\s*ISNUMBER\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m10 = isNumber.matcher(res);
		if (m10.find())
		{
			res = "FALSE";
			int i = Alfabet.parseChar(m10.group(1));
			int j = Integer.parseInt(m10.group(2));
			try
			{
				int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
				res = "TRUE";
			}
			catch (Exception e)
			{
				res = "FALSE";
			}
		}
		return res;
	}

}
