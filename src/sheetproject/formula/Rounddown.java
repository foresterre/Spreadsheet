package sheetproject.formula;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the value rounded down.
 * Arguments: {formula, cell, value}, num_digits
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Rounddown
{
    static Pattern formulaPattern = Pattern.compile("\\s*ROUNDDOWN\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
    
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
            
            double temp1 = 0;
            try
            {
                    temp1 = Double.parseDouble(group1);
            }
            catch(Exception e)
            {
                    
            }
            
            int temp2 = 0;
            try
            {
                    temp2 = Integer.parseInt(group2);
            }
            catch(Exception e)
            {
                    
            }                                              
                    
            BigDecimal bd = new BigDecimal(temp1);
            bd = bd.setScale(temp2, BigDecimal.ROUND_DOWN);
            return Double.toString(bd.doubleValue());
        }
        return res;
    }
}
