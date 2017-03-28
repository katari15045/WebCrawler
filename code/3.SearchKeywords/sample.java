import java.util.Scanner;
import java.util.LinkedHashSet;

public class sample
{
	private static String query;
	private static String format;
	private static int resultCount;
	private static String userId;
	private static String code;

	private static StringBuilder url;
	private static LinkedHashSet<String> result;
	private static HttpClient httpClient;
	private static Scanner scanner;

	public static void main(String[] args) throws Exception
	{
		takeUserInput();
		makeURL();
		httpClient = new HttpClient(url, resultCount);
		httpClient.sendGetRequest();
		result = httpClient.getResult();
		System.out.println("----------------------------------------------------");
		System.out.println(result);
	}

	private static void takeUserInput()
	{
		scanner = new Scanner( System.in );
		System.out.print("Key Words -> ");
		query = scanner.nextLine();
	}

	private static void makeURL()
	{
		url = new StringBuilder();
		format = "xml";
		resultCount = 20;
		userId = "138";
		code = "1461895544";

		url.append("https://www.gigablast.com/search?q=");
		url.append(query);
		url.append("&format=");
		url.append(format);
		url.append("&n=");
		url.append( Integer.toString(resultCount) );
		url.append("&userid=");
		url.append(userId);
		url.append("&code=");
		url.append(code);
	}
}