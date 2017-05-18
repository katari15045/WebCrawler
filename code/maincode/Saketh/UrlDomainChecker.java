
public class UrlDomainChecker
{
	private String firstString;
	private String secondString;

	private String firstDomain;
	private String secondDomain;

	public boolean areTheyInSameDomain(String inpFirstString, String inpSecondString)
	{
		firstString = inpFirstString;
		secondString = inpSecondString;

		prepareFirstDomain();
		prepareSecondDomain();

		if( firstDomain.equals(secondDomain) )
		{
			return true;
		}
	
		return false;
	}

	private void prepareFirstDomain()
	{
		String[] firstStrArray = firstString.split("/");
		int count = 0;

		for( String str:firstStrArray )
		{
			if( count == 2 )
			{
				firstDomain = str;
			}

			count = count + 1;
		}
	}

	private void prepareSecondDomain()
	{
		String[] secondStrArray = secondString.split("/");
		int count = 0;

		for( String str:secondStrArray )
		{
			if( count == 2 )
			{
				secondDomain = str;
			}

			count = count + 1;
		}
	}

}