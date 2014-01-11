package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class Min
{

        static Pattern formulaPattern = Pattern.compile("\\s*MIN\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
        	String res = "";
            
            Matcher m = formulaPattern.matcher(formula);
            if (m.find())
            {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                    String group2 = m.group(2);
                    group2 = Parser.evaluate(group2, data);
                    
    				double min = Double.MAX_VALUE;
    				
    				try
                    {
    						Double parsedValue = Double.parseDouble(group1);
                            if (parsedValue < min)
                            {
                            	min = parsedValue;
                            }
                    }
                    catch(Exception e)
                    {
                            
                    }
                    
                    try
                    {
                    		Double parsedValue = Double.parseDouble(group2);
                            if (parsedValue < min)
                            {
                            	min = parsedValue;
                            }
                    }
                    catch(Exception e)
                    {
                            
                    }
    				
    				return Double.toString(min); 
            }
            return res;
        }
}
