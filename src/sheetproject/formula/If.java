package sheetproject.formula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;
import sheetproject.spreadsheet.Sheet;

/* BEFORE BUILD:
 * Add jython to the classpath
 * java -cp "./;jython-2.5.3.jar" jythonEx
 * OR Eclipse -> Alt+Enter with focus on Spreadsheet( =Project properties) -> Java Build Path -> Add Jar -> jython-2.5.3.jar
 * 
 * 
 */

public class If
{
	// TODO: Working it out
	// Eval staat standaard uit in jvm
	// http://stackoverflow.com/questions/1016128/where-is-java-scripting-engine-used
	// http://learnpythonthehardway.org/book/ex27.html
	// http://docs.python.org/2/
	// http://docs.oracle.com/javase/6/docs/api/javax/script/ScriptEngineManager.html#getEngineByName%28java.lang.String%29
  	
	// =IF(CONDITION, IF_TRUE, IF_FALSE)
	
	
	private static String condition;
	private static boolean evalResult = false;
	static Pattern formulaPattern = Pattern.compile("\\s*IF\\(\\s*([0-9]+|[0-9]+\\.[0-9]+|[A-Z]{1,2}[0-9]{1,6}|[A-Z]{2,10}\\(.*\\)|[a-zA-Z0-9\\s=]+)\\s*,\\s*([a-zA-Z\\s0-9]+)\\s*,\\s*([a-zA-Z\\s0-9]+)\\s*\\)\\s*");
	
  	
	public static String evaluate(String formula, Sheet data) throws CharacterOutOfBoundsException, IllegalFormulaException, ScriptException 
	{
		String res = "";
		     
        Matcher m = formulaPattern.matcher(formula);
        
        // Make sure the statement is parseable by python
        evalConditionSplitter(formula);
        
        if (m.find())
        {
                	
        	String group1 = m.group(1);
        	group1 = Parser.evaluate(group1, data);
        	condition = group1;
        	
        	String group2 = m.group(2);
        	String group3 = m.group(3);
            
        	// Use jython as a python script engine
        	ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
            evalResult = (boolean) engine.eval(condition);
        	
        	if (evalResult == true)
        	{
        		group2 = Parser.parse(group2, data);
        		return group2;
        	}
        	else
        	{
        		group3 = Parser.parse(group3, data);
        		return group3;
        	}
                       	
        }
        return res;
	}
	
	/**
	 * Avaible:
	 * <, >, =, <=, =>
	 * @param s
	 */
	public static void evalConditionSplitter(String s)
	{
		String[] keep;
		
		// Not sure if I'm going to use || or else if's
		if(s.contains("<") || s.contains(">") || s.contains("=") || s.contains("<=") || s.contains(">="))
		{
			// TODO: less than
			// REGEX voor <
			keep = s.split("__REGEX__");
			condition = "";
					
			// Replace = with pythons 'is'
			condition = condition.replace("=", "is");
		}
		else if(s.contains(">"))
		{
			// TODO: more than
			keep = s.split("");
		}
		else if(s.contains("="))
		{
			// TODO: equal
			keep = s.split("");
		}
		else if(s.contains("<="))
		{
			// TODO: less or equal
			keep = s.split("");
		}
		else if(s.contains(">="))
		{
			// TODO: more or equal
			keep = s.split("");
		}
		else
		{
			// TODO: Unparseable
			condition = "False";
		}
		
	}
	
	/**
	 * TEST
	 * @param args
	 * @throws NullObjectException 
	 * @throws IndexOutOfBoundsException 
	 * @throws CharacterOutOfBoundsException 
	 * @throws IllegalFormulaException 
	 */
	public static void main(String[] args) throws ScriptException, IndexOutOfBoundsException, NullObjectException, IllegalFormulaException, CharacterOutOfBoundsException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
        engine.eval("import sys");
        engine.eval("print sys");

        
        
        engine.put("a", 42);
        engine.eval("print a");
        engine.eval("x = 2 + 2");
        engine.eval("y=4<5");
        engine.eval("print y");
        
        
        
        Object x = engine.get("x");
        System.out.println("x: " + x);
        
        
        //new
//        Sheet sheet = new Sheet();
//		Cell a = new Cell("6");
//		Cell b = new Cell("12");
//		sheet.setCell(a, 1, 1);
//		sheet.setCell(b, 1, 2);
//		
//		String z = Parser.parse("=SUM(6,PRODUCT(A1,A2))", sheet);
//		
//		String q = Parser.parse("=IF(A1=A2,A1,A2)", sheet);
//		System.out.println(z);
    }
}
