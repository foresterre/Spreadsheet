package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that counts the cells that contain numbers.
 * Arguments: {formula, number, cell} operator {formula, number, cell}, value true, value false 
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class If
{ 	
	static Pattern formulaPattern = Pattern.compile("\\s*IF\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*(<|<=|=|!=|>=|>)\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|\".{1,20}\"|[^,]{1,20})\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|\".{1,20}\"|[^,]{1,20})\\s*\\)\\s*");
	
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{
		String res = "";
		     
        Matcher m = formulaPattern.matcher(formula);
        
        if (m.find())
        {
                	
        	String group1 = m.group(1);
        	group1 = Parser.evaluate(group1, data);
        	
        	String group3 = m.group(3);
        	group3 = Parser.evaluate(group3, data);
        	
        	String group2 = m.group(2);
     
        	
        	boolean eval = false;
        	
        	
    		if(group2.equals("<"))
    		{		
    			try
    			{
    				double a;
    				double b;
    				
    				a = Double.parseDouble(group1);
    				b = Double.parseDouble(group3);
    				
    				if (a<b)
    				{
    					eval = true;
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}        			
    		}
    		else if(group2.equals("<="))
    		{
    			try
    			{
    				double a;
    				double b;
    				
    				a = Double.parseDouble(group1);
    				b = Double.parseDouble(group3);
    				
    				if (a<b || a==b)
    				{
    					eval = true;
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}
    		}
    		else if(group2.equals("="))
    		{
    			if (group1.equals(group3))
				{
					eval = true;
				}       			
    		}
    		else if(group2.equals("!="))
    		{
    			try
    			{
    				double a;
    				double b;
    				
    				a = Double.parseDouble(group1);
    				b = Double.parseDouble(group3);
    				
    				if (a!=b)
    				{
    					eval = true;
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}
    		}
    		else if(group2.equals(">="))
    		{
    			try
    			{
    				double a;
    				double b;
    				
    				a = Double.parseDouble(group1);
    				b = Double.parseDouble(group3);
    				
    				if (a>b || a==b)
    				{
    					eval = true;
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}
    		}
    		else //if(group2.equals(">"))
    		{
    			try
    			{
    				double a;
    				double b;
    				
    				a = Double.parseDouble(group1);
    				b = Double.parseDouble(group3);
    				
    				if (a>b)
    				{
    					eval = true;
    				}
    			}
    			catch(Exception e)
    			{
    				
    			}
    		}    		        	
        	
        	if (eval)
        	{
        		String group4 = m.group(4);
        		if (group4.startsWith("\"")) 
        		{        			
        			return group4.replaceAll("\"", "");
        		} 
        		else 
        		{
        			group4 = Parser.evaluate(group4, data);
                	return group4;
        		}
            	
        	}
        	else
        	{
        		String group5 = m.group(5);
        		if (group5.startsWith("\""))
        		{
        			return group5.replaceAll("\"", "");
        		} 
        		else 
        		{
        			group5 = Parser.evaluate(group5, data);
                	return group5;
        		}
        	}
                       	
        }
        return res;
	}
}