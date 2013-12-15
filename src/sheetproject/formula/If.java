package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.spreadsheet.Sheet;


public class If
{
		
		// TODO
		// Eval staat standaard uit in jvm
        static Pattern formulaPattern = Pattern.compile("\\s*IF\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z0-9\\s=]+)\\s*,\\s*([a-zA-Z\\s0-9]+)\\s*,\\s*([a-zA-Z\\s0-9]+)\\s*\\)\\s*");
        
        public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
        {
                String res = "";
                
                Matcher m = formulaPattern.matcher(formula);
                if (m.find())
                {
                        String group1 = m.group(1);
                        group1 = Parser.evaluate(group1, data);
                        String group2 = m.group(2);
                        String group3 = m.group(3);
                        
                        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
                        
                        if ()
                        {
                        	return group2;
                        }
                        else
                        {
                        	return group3;
                        }
                        	
                }
                return res;
        }
}
