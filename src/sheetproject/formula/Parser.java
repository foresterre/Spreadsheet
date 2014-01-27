package sheetproject.formula;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

public class Parser
{
	/**
	 * First allowed format
	 */
	static Pattern format = Pattern.compile("(([A-Z]{2,10})\\((.*)\\))");
	
	/**
	 * Second allowed format
	 */
	static Pattern format2 = Pattern.compile("\\s*([A-Z]{1,2})([0-9]{1,6})\\s*");

	/**
	 * Array of String objects containing all the allowed formula's
	 */
	private static String[] allowedFormulas = { "Average", "Count", "Counta", "Countif", "If", "Int", "Islogical",
			"Iseven", "Isnumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper",
			"Rounddown", "Roundup", "Sign", "Sqrt", "Sum", "Sumif" };
	
	/**
	 * Method which removes spaces and other blank symbols, which checks if the formula starts with an equal sign.
	 * Lets the parser evaluate to formula if it starts with the equals sign.
	 * @param formula: formula to be parsed
	 * @param data: data of the sheet object
	 * @return String of the formula without space and which has an equal sign
	 * @throws IllegalFormulaException
	 * @throws CharacterOutOfBoundsException
	 */
	public static String parse(String formula, Sheet data) throws IllegalFormulaException, CharacterOutOfBoundsException
	{
		formula = formula.trim();
		if (formula.startsWith("="))
		{
			formula = evaluate(formula, data);
		}
		return formula;
	}

	/**
	 * Parses the formula
	 * @param formula: formula to be parsed
	 * @param data: data of the sheet object
	 * @return returns the evaluation of the formula
	 * @throws IllegalFormulaException
	 * @throws CharacterOutOfBoundsException
	 */
	public static String evaluate(String formula, Sheet data) throws IllegalFormulaException, CharacterOutOfBoundsException
	{
		Matcher m = format.matcher(formula);
		Matcher m2 = format2.matcher(formula);
		String res = formula;

		if (m.find())
		{
			String className = m.group(2);
			className = className.toLowerCase();
			className = Character.toUpperCase(className.charAt(0)) + className.substring(1);

			if (containsFormula(className))
			{
				try
				{
					className = "sheetproject.formula." + className;
					
					Class c = Class.forName(className);
					Class[] methodParameters = new Class[2];
					
					methodParameters[0] = String.class;
					methodParameters[1] = Sheet.class;
					
					Object[] parameters = new Object[2];
					
					parameters[0] = formula;
					parameters[1] = data;
					
					Method method = c.getMethod("evaluate", methodParameters);
					
					return (String) method.invoke(null, parameters);
				}
				catch (ClassNotFoundException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (NoSuchMethodException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (SecurityException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (IllegalAccessException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (IllegalArgumentException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (InvocationTargetException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				catch (Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
			}
			else
			{
				throw new IllegalFormulaException();
			}
		}
		else if (m2.find())
		{
			int i = Alphabet.parseChar(m2.group(1));
			int j = Integer.parseInt(m2.group(2));
			String cellContent = "";
			try{
				cellContent = data.getCell(i, j).getFormula();
			}
			catch (NullPointerException e){				
			
			}
			cellContent = Parser.evaluate(cellContent, data);
			return cellContent;
		}
		return res;
	}

	/**
	 * Check if the formula is in the allowedFormulas list, and thus is allowed
	 * @param formula: the formula to check if it is in the list
	 * @return true if it is allowed, otherwise false
	 */
	public static boolean containsFormula(String formula)
	{
		for (String formulaName : allowedFormulas)
		{
			if (formula.equals(formulaName))
			{
				return true;
			}
		}
		return false;
	}

}
