package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class Average
{

        static Pattern sum = Pattern.compile("\\s*AVERAGE\\(\\s*([0-9]+|[A-Z][0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*([0-9]+|[A-Z][0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
                String res = "";
                
                Matcher m = sum.matcher(formula);
                if (m.find())
                {
                        String group1 = m.group(1);
                        group1 = Parser.evaluate(group1, data);
                        String group2 = m.group(2);
                        group2 = Parser.evaluate(group2, data);
                                                
                        int number1 = 0;
                        try
                        {
                                number1 = Integer.parseInt(group1);
                        }
                        catch(Exception e)
                        {
                                
                        }
                        
                        int number2 = 0;
                        try
                        {
                                number2 = Integer.parseInt(group2);
                        }
                        catch(Exception e)
                        {
                                
                        }
                        
                        res = Integer.toString(number1 + number2);     
                }
                return res;
        }
}
