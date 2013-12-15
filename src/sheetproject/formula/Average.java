package sheetproject.formula;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;


public class Average extends AbstractFormula 
{
	
	@Override
	public String parse(String formula) 
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern average = Pattern.compile("\\s*AVERAGE\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");
	
	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m13 = average.matcher(res);
		
		if (m13.find())
		{
			int i = Alfabet.parseChar(m13.group(1));
			int j = Integer.parseInt(m13.group(2));
			int k = Alfabet.parseChar(m13.group(3));
			int l = Integer.parseInt(m13.group(4));
			
			ArrayList<Cell> list = new ArrayList<Cell>();
			
			if (i == k)
			{
				for (int q = j; q < l+1; q++)
				{
					list.add(sheet.getCell(i, q));
				}
			}
			
			int temp = 0;
			
			for (Cell c : list)
			{
				temp += Integer.parseInt(c.getFormula());
			}
			
			temp = temp/list.size();
			
			res = Integer.toString(temp);
		}
		
		return res;
	
	}

}
