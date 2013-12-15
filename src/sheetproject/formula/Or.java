package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Or extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern average = Pattern.compile("\\s*OR\\(\\s*([A-Z][0-9])\\s*,\\s*([A-Z][0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = average.matcher(res);
		
		if (m.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m.group(1));
			int j = Integer.parseInt(m.group(2));
			int k = Alfabet.parseChar(m.group(3));
			int l = Integer.parseInt(m.group(4));
			try
			{
				String res2 = sheet.getCell(i, j).getFormula();
				String res3 = sheet.getCell(k, l).getFormula();
				if (res2.equals("TRUE") && res3.equals("TRUE"))
				{
					res = "TRUE";
				}
				else if (res2.equals("TRUE") && res3.equals("FALSE"))
				{
					res = "TRUE";
				}
				else if (res2.equals("FALSE") && res3.equals("TRUE"))
				{
					res = "TRUE";
				}
				else if (res2.equals("FALSE") && res3.equals("FALSE"))
				{
					res = "FALSE";
				}
			}
			catch(Exception e)
			{
				res = "NOT LOGICAL";
			}
		}
		
		return res;
	}

}
