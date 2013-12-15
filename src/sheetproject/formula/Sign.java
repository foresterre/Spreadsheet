package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Sign extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern sign = Pattern.compile("\\s*SIGN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m16 = sign.matcher(res);
		if (m16.find())
		{
			int i = Alfabet.parseChar(m16.group(1));
			int j = Integer.parseInt(m16.group(2));
			try
			{
				int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
				int res3 = 0;
				if (res2 > 0)
				{
					res3 = 1;
				}
				else if (res2 == 0 ) 
				{
					res3 = 0;
				} else if (res2 < 0)
				{
					res3 = -1;
				}
				res = Integer.toString(res3);
			}
			catch (Exception e)
			{
				res="NOT A NUMBER";
			}
		}
		
		return res;
	
	}

}
