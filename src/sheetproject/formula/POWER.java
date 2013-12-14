package sheetproject.formula;


public class Power extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public double execute(double a, double b)
	{
		return Math.pow(a, b);
	}

}
