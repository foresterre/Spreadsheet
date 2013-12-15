package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class IsEven extends AbstractFormula 
{
	
	@Override
	public String parse(String formula) 
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern isEven = Pattern.compile("\\s*ISEVEN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m11 = isEven.matcher(res);
		
		if (m11.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m11.group(1));
			int j = Integer.parseInt(m11.group(2));
			try
			{
				int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
				if ( (res2 & 1) == 0 )
				{
					res = "TRUE";
				}
				else
				{
					res = "FALSE";
				}
			}
			catch (Exception e)
			{
				res = "NOT A NUMBER";
			}
		}
		return res;
	}

}
