package sheetproject.alfabet;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.NumberOutOfBoundsException;

/**
 * Class with useful tools for the alfabet
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Alfabet {
	
	private static String[] list = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	
	public static String parseInt(int i) throws NumberOutOfBoundsException
	{
		if (i >= 1 && i <= 26)
		{
			return list[i-1];
		}
		else if (i > 26 && i <= 701)
		{
			int k = i / 26;
			int l = i - (k * 26);
			return list[k-1] + list[l-1];
		}
		else
		{
			throw new NumberOutOfBoundsException();
		}
	}
	
	public static int parseChar(String i) throws CharacterOutOfBoundsException
	{
		i = i.toUpperCase();
		for (int j = 0; j < list.length; j++)
		{
			if (list[j].equals(i))
			{
				return j + 1;
			}
			
			for(int k = 0; k < list.length; k++)
			{
				if ((list[j] + list[k]).equals(i))
				{
					return (Alfabet.parseChar(list[j]) * 26) + k + 1;
				}
			}
			
		}
		throw new CharacterOutOfBoundsException();
	}
}
