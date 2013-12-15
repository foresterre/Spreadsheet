package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class RoundDown extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern roundDown = Pattern.compile("\\s*ROUNDDOWN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m15 = roundDown.matcher(res);
		if (m15.find())
		{
			int i = Alfabet.parseChar(m15.group(1));
			int j = Integer.parseInt(m15.group(2));
			try
			{
				double res2 = Double.parseDouble(sheet.getCell(i, j).getFormula());
				res2 = Math.floor(res2);
				res = Double.toString(res2);
				
			}
			catch (Exception e)
			{
				res="NOT A NUMBER";
			}
		}
		
		return res;
	
	}

}
