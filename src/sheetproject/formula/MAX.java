package sheetproject.formula;


public class MAX extends Formula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public double execute(double a, double b)
	{
		if (a > b)
		{
			return a;
		}
		else
		{
			return b;
		}
	}

}
