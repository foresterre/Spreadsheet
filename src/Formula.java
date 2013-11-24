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
	static Pattern devide = Pattern.compile("([0-9]+)\\s*\\/\\s*([0-9]+)");

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
		} else
		{
			res = formula.trim();			
		}
		
		return res;
	}
	
}
