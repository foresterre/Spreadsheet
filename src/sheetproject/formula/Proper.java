package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class Proper
{

        static Pattern formulaPattern = Pattern.compile("\\s*PROPER\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z\\s]+)\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
        	String res = "";
            
            Matcher m = formulaPattern.matcher(formula);
            if (m.find())
            {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                    
                    String[] strings = group1.split("\\s+");
                    
                    for(int i = 0; i < strings.length; i++)
                    {
                    	String string = strings[i];
                    	
                    	if (string.length() > 1)
                    	{
	                    	string = string.toLowerCase();
	                    	res += Character.toUpperCase(string.charAt(0)) + string.substring(1);	                    		                    	
                    	}
                    	else
                    	{
                    		string = string.toLowerCase();
	            			res += Character.toUpperCase(string.charAt(0));
                    	}
                    	if (i != strings.length -1)
                    	{
                    		res += " ";
                    	}
                    }
            }
            return res;
        }
}
