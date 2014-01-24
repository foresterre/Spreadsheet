package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the opposite of a logical value.
 * Arguments: formula, cell, letters
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Not
{

        static Pattern formulaPattern = Pattern.compile("\\s*NOT\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z]+)\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
        	String res = "NOT A LOGICAL VALUE";
            
            Matcher m = formulaPattern.matcher(formula);
            if (m.find())
            {
                    String group1 = m.group(1);
                    group1 = Parser.evaluate(group1, data);
                    
                    if (group1.toUpperCase().equals("TRUE"))
                    {
                    	return "FALSE";
                    }
                    else if (group1.toUpperCase().equals("FALSE"))
                    {
                    	return "TRUE";
                    }                    
            }
            return res;
        }
}
