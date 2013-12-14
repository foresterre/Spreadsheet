package sheetproject.formula;


public class IsEven extends AbstractFormula 
{
	
	@Override
	public String parse(String formula) 
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public boolean execute(double numberA)
	{
		if ((numberA % 2) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
