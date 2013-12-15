package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Power extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern power = Pattern.compile("([0-9]+)\\s*\\^\\s*([0-9]+)");
	static Pattern power2 = Pattern.compile("\\s*POWER\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m5 = power.matcher(res);
		if (m5.find())
		{
			int i = Integer.parseInt(m5.group(1));
			int j = Integer.parseInt(m5.group(2));
			int k = (int) Math.pow(i, j);
			res = Integer.toString(k);
		}
		
		Matcher m8 = power2.matcher(res);
		if (m8.find())
		{
			int i = Alfabet.parseChar(m8.group(1));
			int j = Integer.parseInt(m8.group(2));
			int k = Alfabet.parseChar(m8.group(3));
			int l = Integer.parseInt(m8.group(4));
			
			int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
			int res3 = Integer.parseInt(sheet.getCell(k, l).getFormula());
			
			int k2 = (int) Math.pow(res2, res3);
			res = Integer.toString(k2);
		}
	
		return res;
	}

}
