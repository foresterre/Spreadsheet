package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Sum extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern plus = Pattern.compile("([0-9]+)\\s*\\+\\s*([0-9]+)");
	
	// TODO m2 matcher
	static Pattern sum = Pattern.compile("");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = plus.matcher(res);
		if (m.find())
		{
			res = Integer.toString((Integer.parseInt(m.group(1)) + Integer.parseInt(m.group(2))));
		}
		
		Matcher m2 = plus.matcher(res);
		if (m2.find())
		{
			res = Integer.toString((Integer.parseInt(m2.group(1)) + Integer.parseInt(m2.group(2))));
		}
		
		return res;
	
	}
	

}
