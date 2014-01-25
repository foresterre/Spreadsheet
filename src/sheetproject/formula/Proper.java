package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns all words with the first letter characterized and the rest to lower case. 
 * Arguments: formula, cell, text
 * 
 * =PROPER(PARAM)
 * The string PARAM will be set to Proper Case (also known as Camel Case)
 * 
 * Can not use ranges
 * Can not be nested
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Proper
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*PROPER\\(\\s*([A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|\".{1,20}\")\\s*\\)\\s*");
	
	/**
	 * EValuation of the Proper formula
	 * @param formula: the formula to be parsed
	 * @param data: the data to be parsed
	 * @return A string in proper case (Camel Case)
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
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

			String[] strings = group1.split("\\s+");

			for (int i = 0; i < strings.length; i++)
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
				if (i != strings.length - 1)
				{
					res += " ";
				}
			}
		}
		return res;
	}
}
