package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the max of given values. 
 * Arguments: formula, number, cell OR range
 * 
 * =MAX(PARAM1,PARAM2)
 * PARAM1 and PARAM2 have to be numbers
 * Compares PARAM1 and PARAM2. Will return the maximum value of PARAM1 and PARAM2
 * Example: =MAX(3,A4) with cell A4 = 5 will return 5
 * 
 * or
 * 
 * =MAX(COORD1:COORD2)
 * COORD1 and COORD2 are coordinates.
 * Will return the maximum value of a number in the range.
 * 
 * This function ignores strings and can be nested
 * Nested example: =MAX(MAX(3,4),8) returns 8
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Max
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*MAX\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");

	/**
	 * Evaluation of the Max formula
	 * @param formula: the formula to be parsed 
	 * @param data: the data of the sheet object
	 * @return the maximum value of two parameters or a range
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

				double max = Double.MIN_VALUE;

				for (int i = beginColumn; i <= endColumn; i++)
				{
					for (int j = beginRow; j <= endRow; j++)
					{
						String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
						try
						{
							double res2 = Double.parseDouble(content);

							if (res2 > max)
							{
								max = res2;
							}
						}
						catch (Exception e)
						{

						}
					}
				}
				res = Double.toString(max);
			}
			else
			{
				String group2 = m.group(2);
				group2 = Parser.evaluate(group2, data);
				String group3 = m.group(3);
				group3 = Parser.evaluate(group3, data);

				double max = Double.MIN_VALUE;

				try
				{
					Double parsedValue = Double.parseDouble(group2);
					if (parsedValue > max)
					{
						max = parsedValue;
					}
				}
				catch (Exception e)
				{

				}

				try
				{
					Double parsedValue = Double.parseDouble(group3);
					if (parsedValue > max)
					{
						max = parsedValue;
					}
				}
				catch (Exception e)
				{

				}

				return Double.toString(max);
			}
		}
		return res;
	}
}
