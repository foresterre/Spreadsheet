package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;



public class If
{ 	
	// =IF(CONDITION, IF_TRUE, IF_FALSE)
	
	private static boolean evalResult = false;
	
	
	static Pattern formulaPattern = Pattern.compile("\\s*IF\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*(<|<=|=|!=|>=|>)\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z0-9]{1,20})\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z0-9]{1,20})\\s*\\)\\s*");
	
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
        	
        	switch (group2)
        	{
        		case "<":
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
        			
        			break;
        		case "<=":
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
        			
        			break;
        		case "=":
        			try
        			{
        				double a;
        				double b;
        				
        				a = Double.parseDouble(group1);
        				b = Double.parseDouble(group3);
        				
        				if (a==b)
        				{
        					eval = true;
        				}
        			}
        			catch(Exception e)
        			{
        				
        			}
        			
        			break;
        		case "!=":
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
        			
        			break;
        		case ">=":
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
        			
        			break;
        		case ">":
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
        			
        			break;
        	}
        	
        	if (eval)
        	{
        		String group4 = m.group(4);
            	group4 = Parser.evaluate(group4, data);
            	return group4;
        	}
        	else
        	{
        		String group5 = m.group(5);
            	group5 = Parser.evaluate(group5, data);
            	return group5;
        	}
                       	
        }
        return res;
	}
}