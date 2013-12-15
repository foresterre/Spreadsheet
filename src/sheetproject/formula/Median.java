package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Median extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern median = Pattern.compile("\\s*MEDIAN\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m18 = median.matcher(res);
		if (m18.find())
		{
			int i = Alfabet.parseChar(m18.group(1));
			int j = Integer.parseInt(m18.group(2));
			int k = Alfabet.parseChar(m18.group(3));
			int l = Integer.parseInt(m18.group(4));
			
			ArrayList<Cell> list = new ArrayList<Cell>();
			
			if (i == k)
			{
				for (int q = j; q < l+1; q++)
				{
					list.add(sheet.getCell(i, q));
				}
			}
			
			int middle = list.size()/2;
		    if (list.size()%2 == 1) 
		    {
		    	res =  (list.get(middle).getFormula());
		    } 
		    else 
		    {
		        res =  Double.toString((Integer.parseInt(list.get(middle-1).getFormula()) + Integer.parseInt(list.get(middle).getFormula())) / 2.0);
		    }
		}
		return res;
	}

}
