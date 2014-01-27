package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Returns the sum if the condition is evaluated TRUE
 * Arguments: formula, cell, value
 * 
 * =SUMIF(PARAM1:PARAM2,COND)
 *  Sum the range from PARAM1 to PARAM2, for which the condition COND returns TRUE
 * 
 * Can be nested
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Sumif
{

	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*SUMIF\\(\\s*([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6})\\s*,\\s*(<|<=|=|!=|>=|>)\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\)\\s*");

	/**
	 * Evaluation of the Sumif formula
	 * 
	 * @param formula The formula to be parsed
	 * @param data The data of the sheet object
	 * @return The sum of a range of values if the condition is true
	 * @throws CharacterOutOfBoundsException
	 * @throws IllegalFormulaException
	 * @throws ScriptException
	 * @throws NumberOutOfBoundsException
	 */
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException, NumberOutOfBoundsException
	{
		String res = "";

		Matcher m = formulaPattern.matcher(formula);

		if (m.find())
		{
			String beginCell = m.group(1);
			String endCell = m.group(2);

			String operator = m.group(3);
			String group4 = m.group(4);
			group4 = Parser.evaluate(group4, data);

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

			double sum = 0.0;
			for (int i = beginColumn; i <= endColumn; i++)
			{
				for (int j = beginRow; j <= endRow; j++)
				{
					if (If.evaluate("IF(" + Alphabet.parseInt(i) + j + operator + group4 + ", true, false)", data).equals("true"))
					{
						sum += Integer.parseInt(Parser.evaluate(Alphabet.parseInt(i) + j, data));
					}
				}
			}
			return Double.toString(sum);
		}

		return res;
	}
}
