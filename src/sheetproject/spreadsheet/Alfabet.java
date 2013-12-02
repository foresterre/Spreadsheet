package sheetproject.spreadsheet;
/**
 * Class with useful tools for alfabet
 * 
 * @author m.olsthoorn
 */
public class Alfabet {
	
	private static String[] list = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	
	public static String parseInt(int i)
	{
		return list[i-1];
	}
	
	public static int parseChar(String i)
	{
		i = i.toUpperCase();
		for (int j = 0; j < list.length; j++ )
		{
			if (list[j].equals(i))
			{
				return j + 1;
			}
		}
		return 0;
	}
}
