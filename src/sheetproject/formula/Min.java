package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the smallest of given values.
 * Arguments: formula, number, cell OR range
 * 
 * =MIN(PARAM1,PARAM2) or =MIN(PARAM3:PARAM4)
 * Determines whether PARAM1 or PARAM2 has the lowest value or which value out of the range between PARAM3 and PARAM4. Returns the lowest value.
 * PARAM1 and PARAM2 have to be numbers
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */

public class Min
{

	/**
	 * 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*MIN\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");

	/**
	 * 
	 * @param formula
	 * @param data
	 * @return
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

				double min = Double.MAX_VALUE;

				for (int i = beginColumn; i <= endColumn; i++)
				{
					for (int j = beginRow; j <= endRow; j++)
					{
						String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
						try
						{
							double res2 = Double.parseDouble(content);

							if (res2 < min)
							{
								min = res2;
							}
						}
						catch (Exception e)
						{

						}
					}
				}
				res = Double.toString(min);
			}
			else
			{
				String group2 = m.group(2);
				group2 = Parser.evaluate(group2, data);
				String group3 = m.group(3);
				group3 = Parser.evaluate(group3, data);

				double min = Double.MAX_VALUE;
				boolean undefined = false;

				try
				{
					Double parsedValue = Double.parseDouble(group2);
					min = parsedValue;
				}
				catch (Exception e)
				{
					undefined = true;
				}

				try
				{
					Double parsedValue = Double.parseDouble(group3);
					if (!undefined)
					{
						if (parsedValue < min)
						{
							min = parsedValue;
						}
					}
					else
					{
						min = parsedValue;
					}
				}
				catch (Exception e)
				{
					if (undefined)
					{
						return "";
					}
				}

				res = Double.toString(min);
			}
		}
		return res;
	}
}
