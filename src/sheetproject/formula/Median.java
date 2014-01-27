package sheetproject.formula;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the median of given values (middle item of ordered list). 
 * Arguments: formula, number, cell OR range
 * 
 * =MEDIAN(PARAM1,PARAM2) or =MEDIAN(PARAM3:PARAM4)
 * Returns the median value of PARAM1 and PARAM2 or the median value of PARAM3 to PARAM4
 * 
 * Can be used with nested formulas
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Median
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*MEDIAN\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");

	/**
	 * Evaluation of the Median formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return the median of a range or two cells
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 * @throws NumberOutOfBoundsException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException, NumberOutOfBoundsException
	{
		String res = "";

		Matcher m = formulaPattern.matcher(formula);
		if (m.find())
		{
			String group1 = m.group(1);
			if (group1.contains(":"))
			{
				String beginCell = m.group(4);
				String endCell = m.group(5);

				Pattern cellPattern = Pattern.compile("\\s*([A-Z]{1,2})([0-9]{1,6})\\s*");

				Matcher m2 = cellPattern.matcher(beginCell);
				Matcher m3 = cellPattern.matcher(endCell);
				m2.find();
				m3.find();

				int beginColumn = Alphabet.parseChar(m2.group(1));
				int beginRow = Integer.parseInt(m2.group(2));

				int endColumn = Alphabet.parseChar(m3.group(1));
				int endRow = Integer.parseInt(m3.group(2));

				if ((beginRow > endRow) || (beginColumn > endColumn))
				{
					return "";
				}

				double[] list = new double[200];
				int count = 0;

				for (int i = beginColumn; i <= endColumn; i++)
				{
					for (int j = beginRow; j <= endRow; j++)
					{
						String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
						try
						{
							double res2 = Double.parseDouble(content);

							list[count] = res2;
							count++;
						}
						catch (Exception e)
						{
							// This catch statement is to catch exceptions that are not important for the executing of our application
						}
					}
				}
				double[] list2 = new double[count];
				for (int i = 0; i < count; i++)
				{
					list2[i] = list[i];
				}

				Arrays.sort(list2);

				if (count % 2 == 0)
				{
					res = Double.toString((list2[(count / 2)] + list2[(count / 2) - 1]) / 2.0);
				}
				else
				{
					res = Double.toString(list[(count - 1) / 2]);
				}

			}
			else
			{
				String group2 = m.group(2);
				group1 = Parser.evaluate(group2, data);
				String group3 = m.group(3);
				group2 = Parser.evaluate(group3, data);

				double temp = 0;
				try
				{
					temp += Double.parseDouble(group1);
				}
				catch (Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}

				try
				{
					temp += Double.parseDouble(group2);
				}
				catch (Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}

				return Double.toString(temp / 2.0);
			}
		}
		return res;
	}
}
