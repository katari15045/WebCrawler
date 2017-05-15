import java.util.Scanner;

public class sample
{
	private static String result;
	private static HttpClient httpClient;

	private static String queryString;
	private static String format;
	private static String resultCount;
	private static String userid;
	private static String code;
	private static StringBuilder url;

	public static void main(String[] args) throws Exception
	{
		
		prepareQueryString();
		format = "xml";
		resultCount = "20";
		userid = "138";
		code  = "1461895544";
		prepareUrl();
	
		httpClient = new HttpClient( url.toString() );
		httpClient.sendGetRequest();
		result = httpClient.getResult();

		//System.out.println(result);
	}

	private static void prepareUrl()
	{
		url = new StringBuilder();
		url.append("https://www.gigablast.com/search?q=").append(queryString).append("&format=").append(format).append("&n=").append(resultCount)
			.append("&userid=").append(userid).append("&code=").append(code);
	}

	private static String takeUserInput()
	{
		Scanner scanner = new Scanner(System.in);
		String inpStr;
		System.out.print("Search for -> ");
		inpStr = scanner.nextLine();

		return inpStr;
	}

	private static void prepareQueryString()
	{
		StringBuilder result = new StringBuilder();

		String userStr = takeUserInput();
		int count = 0;

		for(String str:userStr.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		queryString = result.toString();
	}
}