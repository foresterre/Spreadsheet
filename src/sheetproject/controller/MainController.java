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
 * Main Controller for the project: The project is started from here.
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
	 * Debug variable
	 */
	public static boolean DEBUG = false; 
	
	/**
	 * variable containing the sheet table
	 */
	private Sheet sheet;
	
	/**
	 * variable containing the GUI
	 */
	private static View view;
	
	/**
	 * variable containing the name of the file
	 */
	private File filename;

	/**
	 * Application starting point
	 * 
	 * @param args
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
		this.filename = null;
	}
	
	/**
	 * Method for opening file
	 * 
	 * @param filename The name of the file to read
	 * @return boolean True if file reading succeeded
	 */
	public boolean openFile(File filename)
	{
		boolean succes = false;
		try
		{
			setSheet(XmlDriver.read(filename));
			this.filename = filename;
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
	 * @return boolean True if file save succeeded
	 */
	public boolean saveFile()
	{
		boolean success = false;
		if (this.filename == null)
		{
			if(MainController.DEBUG)
			{
				System.err.println("You have not opened a file yet");
			}	
		} 
		else
		{
			success = saveFileAs(filename);
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
	 * Method for saving current sheet to new file
	 * 
	 * @param filename Name of new file
	 * @return boolean True if file write succeeded
	 */
	public boolean saveFileAs(File filename)
	{
		boolean success = false;
		try
		{
			XmlDriver.write(this.sheet, filename);
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
	 * @return the sheet
	 */
	public Sheet getSheet() 
	{
		return sheet;
	}

	/**
	 * @param sheet Sets a Sheet object to the MainController sheet 
	 */
	public void setSheet(Sheet sheet) 
	{
		this.sheet = sheet;
	}
	
	public File getFilename() 
	{
		return filename;
	}

	public void setFilename(File filename) 
	{
		this.filename = filename;
	}

}
