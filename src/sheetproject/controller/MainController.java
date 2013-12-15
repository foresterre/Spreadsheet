package sheetproject.controller;

import java.io.File;
import java.io.FileNotFoundException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.FileCorruptException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.gui.Gui;
import sheetproject.spreadsheet.Sheet;
import sheetproject.spreadsheet.XmlDriver;


/**
 * Main Controller for the project
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 *
 */
public class MainController {
	
	/**
	 * variable containing the sheet table
	 */
	private static Sheet sheet;
	
	/**
	 * variable containing the GUI
	 */
	private static Gui gui;
	
	/**
	 * variable containing the name of the file
	 */
	private static File filename;

	/**
	 * Application starting point
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		MainController main = new MainController();
		gui = new Gui(main);
		gui.setVisible(true);
		
		//openFile("xml/testRead.xml");
		//saveFileAs("xml/testWriteOut.xml");
	}
	
	/**
	 * Method for opening file
	 * 
	 * @param filename The name of the file to read
	 * @return boolean True if file reading succeeded
	 */
	public static boolean openFile(File filename)
	{
		boolean succes = false;
		try
		{
			setSheet(XmlDriver.read(filename));
			MainController.filename = filename;
			succes = true;
		} 
		catch (FileCorruptException e)
		{
			System.err.println(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		} catch (NullObjectException e) {
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (CharacterOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalFormulaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @return boolean True if file save succeeded
	 */
//	public static boolean saveFile()
//	{
//		boolean success = false;
//		if (filename.equals(""))
//		{
//			System.err.println("You have not opened a file yet");
//		} else
//		{
//			success = saveFileAs(filename);
//		}
//		
//		return success;
//	}
	
	/**
	 * Method for saving current sheet to new file
	 * 
	 * @param filename Name of new file
	 * @return boolean True if file write succeeded
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
	 * @param sheet Sets a Sheet object to the MainController sheet 
	 */
	public static void setSheet(Sheet sheet) {
		MainController.sheet = sheet;
	}

}
