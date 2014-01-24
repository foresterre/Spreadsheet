package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that considers whether a value is logical or not.
 * Arguments: formula, number, cell
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Islogical
{

        static Pattern formulaPattern = Pattern.compile("\\s*ISLOGICAL\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z\\s]+)\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
        	String res = "FALSE";
            
            Matcher m = formulaPattern.matcher(formula);
            if (m.find())
            {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                    
                    if (group1.toUpperCase().equals("TRUE") | group1.toUpperCase().equals("FALSE"))
                    {
                    	return "TRUE";
                    }
                    else
                    {
                    	return "FALSE";
                    }
            }
            return res;
        }
}
