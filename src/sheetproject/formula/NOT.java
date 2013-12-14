package sheetproject.formula;


public class Not extends AbstractFormula 
{
	
	@Override
	public String parse(String formula)
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	// excel heeft geen NOT functie (bij mij).
	// Wat moet NOT gaan doen?
	public boolean execute(boolean a)
	{
		if (a == true)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//of?:
//	public double execute(double a)
//	{
//		if (a < 0)
//		{
//			return ;
//		}
//		else
//		{
//			return ;
//		}
//	}

}
