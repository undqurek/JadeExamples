public class Utils
{
	public static boolean sleep( long millis )
	{
		try
		{
			Thread.sleep( millis );
			
			return true;
		}
		catch ( InterruptedException e )
		{
			return false;
		}
	}
}
