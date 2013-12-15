package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Int extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern int1 = Pattern.compile("\\s*INT\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	// TODO
	// TO be determined
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = int1.matcher(res);
		
		if (m.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m.group(1));
			int j = Integer.parseInt(m.group(2));
			try
			{
				double res2 = Double.parseDouble(sheet.getCell(i, j).getFormula());
				int res3 = (int)Math.round(res2);
				res = Integer.toString(res3);
				
				
			}
			catch (Exception e)
			{
				res = "NOT A NUMBER";
			}
		}
		return res;
	}

}
