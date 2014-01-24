package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that converts value to integer.
 * Arguments: formula, number, cell
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Int
{

        static Pattern formulaPattern = Pattern.compile("\\s*INT\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
                String res = "";
                
                Matcher m = formulaPattern.matcher(formula);
                if (m.find())
                {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                          
                    double temp = 0;
                    try
                    {
                            temp += Double.parseDouble(group1);
                    }
                    catch(Exception e)
                    {
                            
                    }
                    
                    res = Integer.toString((int) Math.floor(temp));   
                }
                return res;
        }
}
