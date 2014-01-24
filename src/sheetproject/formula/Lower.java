package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that converts a string to lower case.
 * Arguments: formula, cell, text
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Lower
{
    static Pattern formulaPattern = Pattern.compile("\\s*LOWER\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|\".{1,20}\")\\s*\\)\\s*");
    
    public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
    {
    	String res = "";
        
        Matcher m = formulaPattern.matcher(formula);
        if (m.find())
        {
        	String group1 = m.group(1);
        	if (!(group1.startsWith("\""))) 
    		{
    			group1 = Parser.evaluate(group1, data);
    		} 
        	group1 = group1.replaceAll("\"", "");
        	
    		return group1.toLowerCase();
        }
        return res;
    }
}
