import java.util.Scanner;

public class sample
{
	private static String url;
	private static String result;
	private static HttpClient httpClient;

	public static void main(String[] args) throws Exception
	{
		String userStr = takeUserInput();
		String queryString = prepareQueryString(userStr);
		System.out.println(queryString);
		//url = "https://www.gigablast.com/search?q=test&format=xml&userid=12345&code=demo";
		//httpClient = new HttpClient(url);
		//httpClient.sendGetRequest();
		//result = httpClient.getResult();

		//System.out.println(result);
	}

	private static String takeUserInput()
	{
		Scanner scanner = new Scanner(System.in);
		String inpStr;
		System.out.print("Search for -> ");
		inpStr = scanner.nextLine();

		return inpStr;
	}

	private static String prepareQueryString(String inpStr)
	{
		StringBuilder result = new StringBuilder();
		int count = 0;

		for(String str:inpStr.split(" "))
		{
			if( count != 0 )
			{
				result.append("\\ ");
			}

			//System.out.println(str);
			result.append(str);

			count = count + 1;
		}

		return result.toString();
	}
}