package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the rest of a divide operation. 
 * Arguments: formula, number, cell
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Mod
{

	/**
	 * 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*MOD\\(\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");

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
			catch (Exception e)
			{
				return "NOT A NUMBER";
			}
			double temp2 = 0;
			try
			{
				temp2 = Double.parseDouble(group2);
			}
			catch (Exception e)
			{
				return "NOT A NUMBER";
			}

			if (temp2 == 0)
			{
				return "DIVIDING BY ZERO";
			}

			double doubleres = temp1 % temp2;

			if ((temp1 < 0 ^ temp2 < 0) && (temp1 % temp2 != 0))
			{
				doubleres += temp2;
			}

			res = Double.toString(doubleres);
		}
		return res;
	}
}
