package sheetproject.formula;


public class Min extends AbstractFormula 
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
