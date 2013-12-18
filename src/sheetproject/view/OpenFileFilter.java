package sheetproject.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class OpenFileFilter extends FileFilter 
{
	
	private String fileExt = "";
	
    private String Ext = ".scarlet";
    private String Ext2 = ".xml";

    public OpenFileFilter() 
    {
        this(".xml");
    }

    public OpenFileFilter(String extension) 
    {
        fileExt = extension;
    }

     public boolean accept(File f) 
     {
        if (f.isDirectory())
        {
            return true;
        }
        return  (f.getName().toLowerCase().endsWith(fileExt) || f.getName().toLowerCase().endsWith(Ext2)); 
    }

    public String getDescription() 
    {
        if(fileExt.equals(Ext))
        {
            return  "Scarlet Files (*" + fileExt + ")";
        }
        else if(fileExt.equals(Ext2))
        {
            return  "XML Files (*" + fileExt + ")";
        }
        else
        {
            return ("Other File");
        }
    }
}
