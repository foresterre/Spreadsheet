package formula;


public class ISEVEN extends Formula 
{
	
	@Override
	public String parse(String formula) 
	{
		// TODO Auto-generated method stub
		return "";
	}
	
	public boolean isEven(long numberA)
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
