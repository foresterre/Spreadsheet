package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Proper extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	static Pattern proper = Pattern.compile("\\s*PROPER\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");

	public static String parse(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		res = formula.trim();
		res = formula.substring(1);
		
		Matcher m11 = proper.matcher(res);
		
		
		if (m11.find())
		{
			res = "UNDEFINED";
			int i = Alfabet.parseChar(m11.group(1));
			int j = Integer.parseInt(m11.group(2));
			try
			{
				String res2 = sheet.getCell(i, j).getFormula();
			    
				StringBuffer sb1 = new StringBuffer();  
			    String[] res3 = res2.split(" ");  
			       
			    for(int k = 0; k < res3.length; k++)
			    {
			    	sb1.append(res3[k].substring(0, 1).toUpperCase());
			    	sb1.append(res3[k].substring(1).toLowerCase());
			    }
			       
			    res = sb1.toString();  
			}
			catch (Exception e)
			{
				res = "WARNING";
			}
		}
		return res;
	}

}
