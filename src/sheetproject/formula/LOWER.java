package sheetproject.formula;


public class LOWER extends Formula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public boolean execute(double a, double b)
	{
		if (a < b)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
