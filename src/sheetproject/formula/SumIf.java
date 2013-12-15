package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Sumif
{

	static Pattern sumIf = Pattern.compile("\\s*SUMIF\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\:\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	
	//TODO requires the if func
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m = sumIf.matcher(res);
		
		if(m.find())
		{
			
		}
		
		return res;
	}

}
