package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Min extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern min = Pattern.compile("\\s*MIN\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m6 = min.matcher(res);
		if (m6.find())
		{
			int i = Alfabet.parseChar(m6.group(1));
			int j = Integer.parseInt(m6.group(2));
			int k = Alfabet.parseChar(m6.group(3));
			int l = Integer.parseInt(m6.group(4));
			
			ArrayList<Cell> list = new ArrayList<Cell>();
			
			if (i == k)
			{
				for (int q = j; q < l+1; q++)
				{
					list.add(sheet.getCell(i, q));
				}
			}
			
			int min = Integer.MAX_VALUE;
			
			for (Cell c : list)
			{
				if (Integer.parseInt(c.getFormula()) < min)
				{
					min = Integer.parseInt(c.getFormula());
				}
			}
			
			res = Integer.toString(min);
		}
		return res;
	}

}
