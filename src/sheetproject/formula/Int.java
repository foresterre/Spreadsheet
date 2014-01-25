package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that converts value to integer. Arguments: formula, number, cell
 * 
 * =INT(PARAM) Changes the type of the PARAM to an integer. PARAM has to be a
 * number.
 * 
 * Nested example: INT(PRODUCT(PARAM1,INT(PARAM2)))
 * Where the nested function is PARAM1 times INT(PARAM2).
 * PARAM2 has to be a number here. 
 * PARAM1 could be a rational number so the nested function can be rational as well.
 * Then the solution of the nested function is converted to an integer and will lose everything after the dot (truncate).
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
	
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*INT\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");

	/**
	 * Evaluation of the Int formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return Integer value of a number
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
			group1 = Parser.evaluate(group1, data);

			double temp = 0;
			try
			{
				temp += Double.parseDouble(group1);
			}
			catch (Exception e)
			{
				// This catch statement is to catch exceptions that are not important for the executing of our application
			}

			res = Integer.toString((int) Math.floor(temp));
		}
		return res;
	}
}
