package sheetproject.alphabet;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.NumberOutOfBoundsException;

/**
 * Class with useful tools for the alphabet
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class Alphabet 
{
	/**
	 * List: an array containing the 26 letters of the alphabet
	 */
	private static String[] list = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	
	/**
	 * Static function that parser the number in the alphabet to corresponding letter
	 * 
	 * @param int The number corresponding with the x-th letter of the alphabet or double letter (Exampple AQ or FT)
	 * @return String Returns the one or two uppercase letters corresponding to i
	 * @throws NumberOutOfBoundsException
	 */
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
	
	
	/**
	 * Static function that parser the letter in the alphabet to corresponding number
	 *  
	 * @param String Parses one or two letters (from A to ZZ) to a corresponding number (from 1 to 701)
	 * @return int The number corresponding to i
	 * @throws CharacterOutOfBoundsException
	 */
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
					return (Alphabet.parseChar(list[j]) * 26) + k + 1;
				}
			}
			
		}
		throw new CharacterOutOfBoundsException();
	}
}
