package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that considers whether a value is even or not. Arguments: formula, number, cell
 * 
 * =ISEVEN(PARAM) This function checks whether the PARAM is even or not. 
 * PARAM has to be a number.
 * Returns TRUE if PARAM is even. 
 * Otherwise it returns FALSE. 
 * If it is not a number, it will return NOT A NUMBER.
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
public class Iseven
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*ISEVEN\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");

	/**
	 * 
	 * @param formula
	 *            : the formula to be parsed
	 * @param data
	 *            : the data of the sheet object
	 * @return TRUE if number is even, FALSE if the number is uneven, NOT A NUMBER if the value is not a number
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException
	{
		String res = "NOT A NUMBER";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			group1 = Parser.evaluate(group1, data);

			int temp = 0;
			try
			{
				temp += (int) Math.floor(Double.parseDouble(group1));
			}
			catch (Exception e)
			{
				return "NOT A NUMBER";
			}

			if ((temp & 1) == 0)
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
