public class Temp
{
	public static void main(String[] args)
	{
		UrlDomainChecker udc = new UrlDomainChecker();
		
		if( udc.areTheyInSameDomain("http://www.mapsofindia.com/ajmer/delhi-to-ajmer-route-map.html", "https://man.mapsofindia.com/ajmer/delhi-to-ajmer-route-map.html") )
		{
			System.out.println("true");
		}

		else
		{
			System.out.println("False");
		}

	}
}