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
	 * 
	 */
	static Pattern format = Pattern.compile("(([A-Z]{2,10})\\((.*)\\))");
	
	/**
	 * 
	 */
	static Pattern format2 = Pattern.compile("\\s*([A-Z]{1,2})([0-9]{1,6})\\s*");

	/**
	 * 
	 */
	private static String[] allowedFormulas = { "Average", "Count", "Counta", "Countif", "If", "Int", "Islogical",
			"Iseven", "Isnumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper",
			"Rounddown", "Roundup", "Sign", "Sqrt", "Sum", "Sumif" };
	
	/**
	 * 
	 * @param formula
	 * @param data
	 * @return
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
	 * 
	 * @param formula
	 * @param data
	 * @return
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
			String classname = m.group(2);
			classname = classname.toLowerCase();
			classname = Character.toUpperCase(classname.charAt(0)) + classname.substring(1);

			if (containsFormula(classname))
			{
				try
				{
					classname = "sheetproject.formula." + classname;
					Class c = Class.forName(classname);
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
					e.printStackTrace();
				}
				catch (NoSuchMethodException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
					e.printStackTrace();
				}
				catch (SecurityException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
					e.printStackTrace();
				}
				catch (IllegalArgumentException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
					e.printStackTrace();
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
			String cellContent = data.getCell(i, j).getFormula();
			cellContent = Parser.evaluate(cellContent, data);
			return cellContent;
		}
		return res;
	}

	/**
	 * 
	 * @param formula
	 * @return
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

	/**
	 * TEST FUNCTIE (TO BE REMOVED__ AT FINAL_UITGECOMMENTE_CODE_PATCH)
	 * @param args
	 * @throws IndexOutOfBoundsException
	 * @throws NullObjectException
	 * @throws IllegalFormulaException
	 * @throws CharacterOutOfBoundsException
	 */
	public static void main(String[] args) throws IndexOutOfBoundsException, NullObjectException, IllegalFormulaException, CharacterOutOfBoundsException
	{
		Sheet data = new Sheet();
		data.setCell(new Cell("6"), 1, 1);
		data.setCell(new Cell("7"), 1, 2);
		data.setCell(new Cell(""), 1, 3);
		data.setCell(new Cell("9"), 2, 1);
		data.setCell(new Cell("768"), 2, 2);
		data.setCell(new Cell("11"), 2, 3);
		String a = Parser.parse("=MEDIAN(A1:B3)", data);
		System.out.println(a);
	}

}
