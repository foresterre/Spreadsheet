package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Not extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	// excel heeft geen NOT functie (bij mij).
	// Wat moet NOT gaan doen?
	static Pattern not = Pattern.compile("\\s*NOT\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = not.matcher(res);
		
		if (m.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m.group(1));
			int j = Integer.parseInt(m.group(2));
			try
			{
				String res2 = sheet.getCell(i, j).getFormula();
				if (res2.equals("TRUE"))
				{
					res = "FALSE";
				}
				else if (res2.equals("FALSE"))
				{
					res = "TRUE";
				}
			}
			catch (Exception e)
			{
				res = "NOT LOGICAL";
			}
		}
		return res;
	}
	

}
