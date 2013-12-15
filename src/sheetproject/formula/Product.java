package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Product extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern times = Pattern.compile("([0-9]+)\\s*\\*\\s*([0-9]+)");
	static Pattern product = Pattern.compile("\\s*PRODUCT\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m3 = times.matcher(res);
		
		if (m3.find())
		{
			res = Integer.toString((Integer.parseInt(m3.group(1)) * Integer.parseInt(m3.group(2))));
		}
		
		Matcher m9 = product.matcher(res);
		if (m9.find())
		{
			int i;
			i = Alfabet.parseChar(m9.group(1));
			
			int j = Integer.parseInt(m9.group(2));
			
			int k;
			k = Alfabet.parseChar(m9.group(3));
			
			
			int l = Integer.parseInt(m9.group(4));
			
			int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
			int res3 = Integer.parseInt(sheet.getCell(k, l).getFormula());
			
			int k2 = res2 * res3;
			res = Integer.toString(k2);
		}
		
		return res;
	}

}
