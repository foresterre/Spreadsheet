package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class IsLogical extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern isLogical = Pattern.compile("\\s*ISLOGICAL\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = isLogical.matcher(res);
		
		if (m.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m.group(1));
			int j = Integer.parseInt(m.group(2));
			try
			{
				String res2 = sheet.getCell(i, j).getFormula();
				if (res2.equals("TRUE") || res2.equals("FALSE"))
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
				res="NOT LOGICAL";
			}
		}
		return res;
	}
	


}
