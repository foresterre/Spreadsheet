package sheetproject.controller;

import java.io.File;
import java.io.FileNotFoundException;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.FileCorruptException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.view.View;
import sheetproject.spreadsheet.Cell;
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
public class MainController 
{
	
	/**
	 * Debug variable
	 */
	public static boolean DEBUG = false; 
	
	/**
	 * Variable containing the sheet table
	 */
	private Sheet sheet;
	
	/**
	 * Variable containing the GUI
	 */
	private static View view;
	
	/**
	 * Variable containing the name of the file
	 */
	private File fileName;

	/**
	 * Application starting point
	 * 
	 * @param String[] Arguments for the application itself
	 */
	public static void main(String[] args) 
	{
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				MainController main = new MainController();
				view = new View(main);
			}
		});
		
		if(MainController.DEBUG)
		{
			System.out.println("DEBUGGING MODE: ON");
		}
	}
	
	/**
	 * Method for new file
	 */
	public void newFile()
	{
		this.setSheet(new Sheet());
		this.fileName = null;
	}
	
	/**
	 * Method for opening file
	 * 
	 * @param File The name of the file to read
	 * @return boolean True if file reading succeeded, otherwise false
	 */
	public boolean openFile(File filename)
	{
		boolean succes = false;
		try
		{
			setSheet(XmlDriver.read(filename));
			this.fileName = filename;
			succes = true;
		} 
		catch (FileCorruptException e)
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}
		} 
		catch (IndexOutOfBoundsException e) 
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}	
		} 
		catch (NullObjectException e) 
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}	
		} 
		catch (FileNotFoundException e) 
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}	
		} 
		catch (CharacterOutOfBoundsException e) 
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}	
		} 
		catch (IllegalFormulaException e) 
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getMessage());
			}	
		}
		return succes;
	}
	
	/**
	 * Method for saving current sheet to file
	 * 
	 * @return boolean True if file save succeeded, otherwise false
	 */
	public boolean saveFile()
	{
		boolean success = false;
		if (this.fileName == null)
		{
			if(MainController.DEBUG)
			{
				System.err.println("You have not opened a file yet");
			}	
		} 
		else
		{
			success = saveFileAs(fileName);
		}
		
		if(success)
		{
			for(String key : this.getSheet().getCells().keySet())
            {
				Cell cell = this.getSheet().getCells().get(key);
				cell.setState(Cell.UPTODATE);     
            }
		}
		
		return success;
	}
	
	/**
	 * Method for saving current sheet to a new file
	 * 
	 * @param File Name of new file
	 * @return boolean True if file write succeeded, false otherwise
	 */
	public boolean saveFileAs(File filename)
	{
		boolean success = false;
		try
		{
			XmlDriver.write(this.sheet, filename);
			this.fileName = filename;
			success = true;
		} 
		catch(Exception e)
		{
			if(MainController.DEBUG)
			{
				System.err.println(e.getStackTrace());
			}	
		}
		
		if(success)
		{
			for(String key : this.getSheet().getCells().keySet())
            {
				Cell cell = this.getSheet().getCells().get(key);
				cell.setState(Cell.UPTODATE);     
            }
		}
		
		return success;
	}

	/**
	 * Method for returning the Sheet object
	 * 
	 * @return Sheet The sheet object
	 */
	public Sheet getSheet() 
	{
		return sheet;
	}

	/**
	 * Method for setting the Sheet object
	 * 
	 * @param Sheet Sets a Sheet object to the MainController sheet 
	 */
	public void setSheet(Sheet sheet) 
	{
		this.sheet = sheet;
	}
	
	/**
	 * Method for obtaining the file name
	 * 
	 * @return File The file name
	 */
	public File getFileName() 
	{
		return fileName;
	}
	
	/**
	 * Method for setting the file name
	 * 
	 * @param File The name of the file
	 */
	public void setFileName(File fileName) 
	{
		this.fileName = fileName;
	}

}
