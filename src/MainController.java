
public class MainController {
	
	/**
	 * variable containing the sheet table
	 */
	private static Sheet sheet;
	
	/**
	 * variable containing the name of the file
	 */
	private static String filename = "";

	/**
	 * Application starting point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		openFile("test.xml");
		saveFileAs("test2.xml");
	}
	
	/**
	 * Method for opening file
	 * 
	 * @param filename
	 * @return boolean True if succeeded
	 */
	public static boolean openFile(String filename)
	{
		boolean succes = false;
		try{
			setSheet(XmlDriver.read(filename));
			MainController.filename = filename;
			succes = true;
		} catch (FileCorruptException e) {
			System.err.println("The file requested is corrupt");
		}
		return succes;
	}
	
	
	/**
	 * Method for new file
	 */
	public static void newFile()
	{
		setSheet(new Sheet());
	}
	
	/**
	 * Method for saving current sheet to file
	 * 
	 * @return boolean True if succeeded
	 */
	public static boolean saveFile()
	{
		boolean success = false;
		if (filename.equals(""))
		{
			System.err.println("You have not opened a file yet");
		} else
		{
			success = saveFileAs(filename);
		}
		
		return success;
	}
	
	/**
	 * Method for saving current sheet to new file
	 * 
	 * @param filename Name of new file
	 * @return boolean True if succeeded
	 */
	public static boolean saveFileAs(String filename)
	{
		boolean success = false;
		try
		{
			XmlDriver driver = new XmlDriver();
			driver.write(sheet, filename);
			success = true;
		} catch(Exception e)
		{
			
		}
		return success;
	}

	/**
	 * @return the sheet
	 */
	public static Sheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public static void setSheet(Sheet sheet) {
		MainController.sheet = sheet;
	}

}
