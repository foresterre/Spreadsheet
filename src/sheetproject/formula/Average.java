package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alphabet.Alphabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NumberOutOfBoundsException;
import sheetproject.spreadsheet.Sheet;


public class Average
{

        static Pattern formulaPattern = Pattern.compile("\\s*AVERAGE\\(\\s*((-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*(-?[0-9]+|-?[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\))|([A-Z]{1,2}[0-9]{1,6})\\s*:\\s*([A-Z]{1,2}[0-9]{1,6}))\\s*\\)\\s*");
        
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
                                
                        }
                        
                        try
                        {
                               temp += Double.parseDouble(group3);
                        }
                        catch(Exception e)
                        {
                                
                        }
                        
                        res = Double.toString(temp / 2);
                    }
                }
                return res;
        }
}
