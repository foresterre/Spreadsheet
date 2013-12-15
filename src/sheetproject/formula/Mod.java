package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Mod extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern mod = Pattern.compile("\\s*MOD\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m17 = mod.matcher(res);
		if (m17.find())
		{
			int i = Alfabet.parseChar(m17.group(1));
			int j = Integer.parseInt(m17.group(2));
			int k = Alfabet.parseChar(m17.group(3));
			int l = Integer.parseInt(m17.group(4));
			
			Double res2 = Double.parseDouble(sheet.getCell(i, j).getFormula());
			Double res3 = Double.parseDouble(sheet.getCell(k, l).getFormula());
			
			Double k2 = res2 % res3;
			res = Double.toString(k2);
		}
		
		return res;
	}

}
