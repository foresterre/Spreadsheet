package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that takes the average of values.
 * Arguments: formula, number, cell OR range
 * 
 * =AVERAGE(PARAM1, PARAM2) 
 * or
 * =AVERAGE(PARAM3:PARAM4)
 * Where PARAM1 and PARAM2 are numbers or coordinates
 * PARAM3:PARAM4 is a range of coordinates from PARAM3 to PARAM4
 * 
 * Nesting formulas can be done like so: =AVERAGE(PARAM1,AVERAGE(PARAM2, PARAM3))
 * If nesting is used, please do not forget the correct amount of parentheses
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Average
{

	/**
	 * Pattern that is used to recognize the formula provided 
	 */
	static Pattern formulaPattern = Pattern.compile("\\s*AVERAGE\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");

	/**
	 * Evaluation of the Average formula
	 * @param formula: the formula to be parsed
	 * @param data: the data of the sheet object
	 * @return Answer value of the average between two parameters
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
			if (group1.contains(":") && !group1.contains("(") && !group1.contains(")"))
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
  	
				if((beginRow > endRow) || (beginColumn > endColumn))
				{
					return "";
				}
                    	
				double avg = 0;
				int count = 0;
                    	
				for (int i = beginColumn; i <= endColumn; i++)
				{
					for (int j = beginRow; j <= endRow; j++)
					{	        		
						String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
						try
						{
							double res2 = Double.parseDouble(content);
                    				
							avg += res2;
							count++;
						}
						catch (Exception e)
						{
							// This catch statement is to catch exceptions that are not important for the executing of our application
						}    
					}
				}
				res =  Double.toString(avg/count);
			}
			else
			{
				String group2 = m.group(2);
				group2 = Parser.evaluate(group2, data);
				String group3 = m.group(3);
				group3 = Parser.evaluate(group3, data);
                              
				double temp = 0;
				try
				{
					temp += Double.parseDouble(group2);
				}
				catch(Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
                        
				try
				{
					temp += Double.parseDouble(group3);
				}
				catch(Exception e)
				{
					// This catch statement is to catch exceptions that are not important for the executing of our application
				}
				
				res = Double.toString(temp / 2);
			}
		}
		return res;
	}
}
