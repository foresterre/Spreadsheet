package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that considers whether a value is numeric or not. Arguments: formula, number, cell
 * 
 * =ISNUMBER(PARAM)
 * If PARAM is a number this function will return true. Otherwise it will return false.
 * 
 * This formula can be nested
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Isnumber
{

	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*ISNUMBER\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");

	/**
	 * Evaluation of the Isnumber formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return TRUE if the parameter is a number, otherwise false
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		String res = "FALSE";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			group1 = Parser.evaluate(group1, data);

			try
			{
				Double.parseDouble(group1);
				return "TRUE";
			}
			catch (Exception e)
			{
				return "FALSE";
			}

		}
		return res;
	}
}
