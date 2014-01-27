package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that returns the product of two values. 
 * Arguments: formula, number, cell OR range
 * 
 * =PRODUCT(PARAM1,PARAM2) or =PRODUCT(PARAM0:PARAM_N)
 * returns PARAM1 times PARAM2 respectively PARAM0 times PARAM_1 times PARAM_2 times ... until PARAM_N
 * Any parameter has to be a number
 * 
 * Can be nested.
 * Example: =PRODUCT(PARAM1,PRODUCT(PARAM2,PARAM3))
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Product
{
	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*PRODUCT\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");
	
	/**
	 * Evaluation of the Product formula
	 * @param formula: the formula to be parsed
	 * @param data the data of the sheet object
	 * @return roduct of value A and value B or of the each value in a range
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
			if (group1.contains(":")  && !group1.contains("(") && !group1.contains(")"))
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

				double product = 1.0;

				for (int i = beginColumn; i <= endColumn; i++)
				{
					for (int j = beginRow; j <= endRow; j++)
					{
						String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
						try
						{
							double temp = Double.parseDouble(content);
							product = product * temp;
						}
						catch (Exception e)
						{
							// This catch statement is to catch exceptions that are not important for the executing of our application
						}
					}
				}
				res = Double.toString(product);
			}
			else
			{
				String group2 = m.group(2);
				group2 = Parser.evaluate(group2, data);
				String group3 = m.group(3);
				group3 = Parser.evaluate(group3, data);

				double temp1 = 0;
				try
				{
					temp1 = Double.parseDouble(group2);
				}
				catch (Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}

				double temp2 = 0;
				try
				{
					temp2 = Double.parseDouble(group3);
				}
				catch (Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}

				res = Double.toString(temp1 * temp2);
			}
		}
		return res;
	}
}
