package sheetproject.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.spreadsheet.*;

/**
 * Class with useful tools for formulas
 * 
 * @author m.olsthoorn
 */
public class Formules {
	
	static Pattern plus = Pattern.compile("([0-9]+)\\s*\\+\\s*([0-9]+)");
	static Pattern minus = Pattern.compile("([0-9]+)\\s*\\-\\s*([0-9]+)");
	static Pattern times = Pattern.compile("([0-9]+)\\s*\\*\\s*([0-9]+)");
	static Pattern product = Pattern.compile("\\s*PRODUCT\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern divide = Pattern.compile("([0-9]+)\\s*\\/\\s*([0-9]+)");
	static Pattern power = Pattern.compile("([0-9]+)\\s*\\^\\s*([0-9]+)");
	static Pattern power2 = Pattern.compile("\\s*POWER\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern sqrt = Pattern.compile("\\s*SQRT\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern min = Pattern.compile("\\s*MIN\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern max = Pattern.compile("\\s*MAX\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern isNumber = Pattern.compile("\\s*ISNUMBER\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern isEven = Pattern.compile("\\s*ISEVEN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern average = Pattern.compile("\\s*AVERAGE\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern roundUp = Pattern.compile("\\s*ROUNDUP\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern roundDown = Pattern.compile("\\s*ROUNDDOWN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern sign = Pattern.compile("\\s*SIGN\\(\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern mod = Pattern.compile("\\s*MOD\\(\\s*([A-Z])([0-9])\\s*,\\s*([A-Z])([0-9])\\s*\\)\\s*");
	static Pattern median = Pattern.compile("\\s*MEDIAN\\(\\s*([A-Z])([0-9])\\s*:\\s*([A-Z])([0-9])\\s*\\)\\s*");

	
	public static String parseFormula(String formula, Sheet sheet) throws CharacterOutOfBoundsException
	{
		String res = "";
		
		if (formula.startsWith("="))
		{
			res = formula.trim();
			res = formula.substring(1);
			
			Matcher m = plus.matcher(res);
			if (m.find())
			{
				res = Integer.toString((Integer.parseInt(m.group(1)) + Integer.parseInt(m.group(2))));
			}
			
			Matcher m2 = minus.matcher(res);
			if (m2.find())
			{
				res = Integer.toString((Integer.parseInt(m2.group(1)) - Integer.parseInt(m2.group(2))));
			}
			
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
			
			Matcher m4 = divide.matcher(res);
			if (m4.find())
			{
				res = Integer.toString((Integer.parseInt(m4.group(1)) / Integer.parseInt(m4.group(2))));
			}
			
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
			
			Matcher m12 = sqrt.matcher(res);
			if (m12.find())
			{
				int i = Alfabet.parseChar(m12.group(1));
				int j = Integer.parseInt(m12.group(2));
				try
				{
					int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
					res = Integer.toString(res2 * res2);
				}
				catch (Exception e)
				{
					res = "NOT A NUMBER";
				}
			}
			
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
			
			Matcher m7 = max.matcher(res);
			if (m7.find())
			{
				int i = Alfabet.parseChar(m7.group(1));
				int j = Integer.parseInt(m7.group(2));
				int k = Alfabet.parseChar(m7.group(3));
				int l = Integer.parseInt(m7.group(4));
				
				ArrayList<Cell> list = new ArrayList<Cell>();
				
				if (i == k)
				{
					for (int q = j; q < l+1; q++)
					{
						list.add(sheet.getCell(i, q));
					}
				}
				
				int max = Integer.MIN_VALUE;
				
				for (Cell c : list)
				{
					if (Integer.parseInt(c.getFormula()) > max)
					{
						max = Integer.parseInt(c.getFormula());
					}
				}
				
				res = Integer.toString(max);
			}
			
			Matcher m10 = isNumber.matcher(res);
			if (m10.find())
			{
				res = "FALSE";
				int i = Alfabet.parseChar(m10.group(1));
				int j = Integer.parseInt(m10.group(2));
				try
				{
					int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
					res = "TRUE";
				}
				catch (Exception e)
				{
					res="FALSE";
				}
			}
			
			Matcher m11 = isEven.matcher(res);
			if (m11.find())
			{
				res = "UNDEFINED";
				int i = Alfabet.parseChar(m11.group(1));
				int j = Integer.parseInt(m11.group(2));
				try
				{
					int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
					if ( (res2 & 1) == 0 )
					{
						res = "TRUE";
					}
					else
					{
						res = "FALSE";
					}
				}
				catch (Exception e)
				{
					res="NOT A NUMBER";
				}
			}
			
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
			
			Matcher m14 = roundUp.matcher(res);
			if (m14.find())
			{
				int i = Alfabet.parseChar(m14.group(1));
				int j = Integer.parseInt(m14.group(2));
				try
				{
					double res2 = Double.parseDouble(sheet.getCell(i, j).getFormula());
					res2 = Math.ceil(res2);
					res = Double.toString(res2);
				}
				catch (Exception e)
				{
					res="NOT A NUMBER";
				}
			}
			
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
			
			Matcher m16 = sign.matcher(res);
			if (m16.find())
			{
				int i = Alfabet.parseChar(m16.group(1));
				int j = Integer.parseInt(m16.group(2));
				try
				{
					int res2 = Integer.parseInt(sheet.getCell(i, j).getFormula());
					int res3 = 0;
					if (res2 > 0)
					{
						res3 = 1;
					}
					else if (res2 == 0 ) 
					{
						res3 = 0;
					} else if (res2 < 0)
					{
						res3 = -1;
					}
					res = Integer.toString(res3);
				}
				catch (Exception e)
				{
					res="NOT A NUMBER";
				}
			}
			
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
			    if (list.size()%2 == 1) {
			    	res =  (list.get(middle).getFormula());
			    } else {
			        res =  Double.toString((Integer.parseInt(list.get(middle-1).getFormula()) + Integer.parseInt(list.get(middle).getFormula())) / 2.0);
			    }
			}
			
		} else
		{
			res = formula.trim();			
		}
		
		return res;
	}
	
}
