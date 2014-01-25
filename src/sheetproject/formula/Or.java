package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the outcome of an or operation 
 * Arguments: formula, cell, letters
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Or
{
	/**
	 * 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*OR\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z]+)\\s*,\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z]+)\\s*\\)\\s*");

	/**
	 * 
	 * @param formula
	 * @param data
	 * @return
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		String res = "NOT A LOGICAL VALUE";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			group1 = Parser.evaluate(group1, data);
			String group2 = m.group(2);
			group2 = Parser.evaluate(group2, data);

			if (group1.toUpperCase().equals("FALSE") && group2.toUpperCase().equals("FALSE"))
			{
				return "FALSE";
			}
			else if (group1.toUpperCase().equals("TRUE") || group2.toUpperCase().equals("TRUE"))
			{
				return "TRUE";
			}
		}
		return res;
	}
}
