import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with useful tools for formulas
 * 
 * @author m.olsthoorn
 */
public class Formula {
	
	static Pattern plus = Pattern.compile("([0-9]+)\\s*\\+\\s*([0-9]+)");
	static Pattern minus = Pattern.compile("([0-9]+)\\s*\\-\\s*([0-9]+)");
	static Pattern times = Pattern.compile("([0-9]+)\\s*\\*\\s*([0-9]+)");
	static Pattern divide = Pattern.compile("([0-9]+)\\s*\\/\\s*([0-9]+)");

	public static String parse(String formula)
	{
		String res = "";
		
		if (formula.startsWith("="))
		{
			res = formula.trim();
			res = formula.substring(1);
			
			Matcher m = plus.matcher(res);
			if (m.find())
			{
				res = Integer.toString((Integer.parseInt(m.group(1)) + Integer.parseInt(m.group(2))));
			}
			
			Matcher m2 = minus.matcher(res);
			if (m2.find())
			{
				res = Integer.toString((Integer.parseInt(m2.group(1)) - Integer.parseInt(m2.group(2))));
			}
			
			Matcher m3 = times.matcher(res);
			if (m3.find())
			{
				res = Integer.toString((Integer.parseInt(m3.group(1)) * Integer.parseInt(m3.group(2))));
			}
			
			Matcher m4 = divide.matcher(res);
			if (m4.find())
			{
				res = Integer.toString((Integer.parseInt(m4.group(1)) / Integer.parseInt(m4.group(2))));
			}
		} else
		{
			res = formula.trim();			
		}
		
		return res;
	}
	
}
