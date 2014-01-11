package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class Sign
{

        static Pattern formulaPattern = Pattern.compile("\\s*SIGN\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
        	String res = "NOT A NUMBER";
            
            Matcher m = formulaPattern.matcher(formula);
            if (m.find())
            {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                    
                    try
                    {
                    	Double temp = Double.parseDouble(group1);
                    	if (temp > 0)
                    	{
                    		return Integer.toString(1);
                    	}
                    	else if (temp < 0)
                    	{
                    		return Integer.toString(-1);
                    	}
                    	else
                    	{
                    		return Integer.toString(0);
                    	}
                    }
                    catch(Exception e)
                    {
                    	return "NOT A NUMBER";
                    }

            }
            return res;
        }
}
