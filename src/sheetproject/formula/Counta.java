package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;

/**
 * Class that counts the cells that are not empty.
 * Arguments: formula, number, cell OR range
 * 
 * =COUNTA(PARAM1,PARAMX)
 * The COUNTA formula counts the amount of filled cells. So if the content in the cell is not empty and it is in range, it is counted.
 * PARAM1:PARAMX is a range between two coordinates
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Counta
{
		/**
		 * Pattern that is used to recognize the formula provided 
		 */
        static Pattern formulaPattern = Pattern.compile("\\s*COUNTA\\(\\s*(([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");
        
        /**
         * Evaluation of the Counta formula
         * @param formula: formula to be parsed
         * @param data: the data of the sheet object
         * @return Counted number of cells of a range between two coordinates, excluding empty cells
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
                    	
                    	if((beginRow > endRow) || (beginColumn > endColumn))
                    	{
                    		return "";
                    	}
                    	
                    	int count = 0;
                    	
                    	for (int i = beginColumn; i <= endColumn; i++)
                    	{
                    		for (int j = beginRow; j <= endRow; j++)
                    		{	        		
                    			String content = Parser.evaluate(Alphabet.parseInt(i) + j, data);
                    			
                    			if (!content.equals(""))
                    			{
                    				count++;
                    			}
                    			  
                    		}
                    	}
                    	res =  Integer.toString(count);
                    }
                    else
                    {
                    	String group2 = m.group(2);
                        group2 = Parser.evaluate(group2, data);
                        String group3 = m.group(3);
                        group3 = Parser.evaluate(group3, data);
                              
                        int temp = 0;                        
                        if (!group2.equals(""))
                        {
                        	temp ++;
                        }
	                	if (!group3.equals(""))
	                    {
	                    	temp ++;
	                    }                           
                        res = Integer.toString(temp);   
                    }
                }
                return res;
        }
}
