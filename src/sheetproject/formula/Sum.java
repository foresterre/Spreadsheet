package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetproject.alfabet.Alfabet;
import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class Sum
{

        static Pattern sum = Pattern.compile("\\s*SUM\\(\\s*([0-9]+|[A-Z][0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*,\\s*([0-9]+|[A-Z][0-9]{1,6}|[A-Z]{2,10}\\(.*\\))\\s*\\)\\s*");
        static Pattern sum2 = Pattern.compile("\\s*([A-Z]{1,2})([0-9]{1,6})\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException 
        {
                String res = "";
                
                Matcher m = sum.matcher(formula);
                if (m.find())
                {
                        String group1 = m.group(1);
                        group1 = Parser.evaluate(group1, data);
                        String group2 = m.group(2);
                        group2 = Parser.evaluate(group2, data);
                        
                        Matcher m2 = sum2.matcher(group1);
                        Matcher m3 = sum2.matcher(group2);
                        
                        int number1 = 0;
                        try
                        {
                                number1 = Integer.parseInt(group1);
                        }
                        catch(Exception e)
                        {
                                
                        }
                        if (m2.find())
                        {
                        	String a = m2.group(0);
                        	String ab = m2.group(1);
                        	String abc = m2.group(2);
                                int i = Alfabet.parseChar(m2.group(1));
                                int j = Integer.parseInt(m2.group(2));
                                String cellContent = data.getCell(i, j).getFormula();
                                cellContent = Parser.evaluate(cellContent, data);
                                number1 = Integer.parseInt(cellContent);
                        }
                        
                        int number2 = 0;
                        try
                        {
                                number2 = Integer.parseInt(group2);
                        }
                        catch(Exception e)
                        {
                                
                        }
                        if (m3.find())
                        {
                                int i = Alfabet.parseChar(m3.group(1));
                                int j = Integer.parseInt(m3.group(2));
                                String cellContent = data.getCell(i, j).getFormula();
                                cellContent = Parser.evaluate(cellContent, data);
                                number2 = Integer.parseInt(cellContent);
                        }
                        
                        res = Integer.toString(number1 + number2);
                        
                }
                return res;
        }
}
