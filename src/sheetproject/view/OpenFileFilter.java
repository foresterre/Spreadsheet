package sheetproject.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Class for the OpenFileFilter
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class OpenFileFilter extends FileFilter
{
	/**
	 * Empty file extension variable
	 */
	private String fileExt = "";
	
	/**
	 * Variables for extensions
	 */
	private String Ext = ".scar";
	private String Ext2 = ".xml";
	
	/**
	 * Constructor for OpenFileFilter
	 */
	public OpenFileFilter()
	{
		this(".xml");
	}
	
	/**
	 * Constructor for OpenFileFilter
	 * @param extension: String containing the extension
	 */
	public OpenFileFilter(String extension)
	{
		fileExt = extension;
	}
	
	/**
	 * Method for accepting the file extensions
	 */
	public boolean accept(File f)
	{
		if (f.isDirectory())
		{
			return true;
		}
		return (f.getName().toLowerCase().endsWith(fileExt) || f.getName().toLowerCase().endsWith(Ext2));
	}
	
	/**
	 * Method to get the description of the OpenFileFilter
	 */
	public String getDescription()
	{
		if (fileExt.equals(Ext))
		{
			return "Scarlett Files (*" + fileExt + ")";
		}
		else if (fileExt.equals(Ext2))
		{
			return "XML Files (*" + fileExt + ")";
		}
		else
		{
			return ("Other File");
		}
	}
}
