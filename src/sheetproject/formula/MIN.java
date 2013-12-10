package sheetproject.formula;


public class MIN extends Formula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public double execute(double a, double b)
	{
		if (a < b)
		{
			return a;
		}
		else
		{
			return b;
		}
	}

}
