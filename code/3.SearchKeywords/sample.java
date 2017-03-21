import java.util.Scanner;

public class sample
{
	private static String query;
	private static String format;
	private static String userId;
	private static String code;

	private static StringBuilder url;
	private static String result;
	private static HttpClient httpClient;
	private static Scanner scanner;

	public static void main(String[] args) throws Exception
	{
		takeUserInput();
		makeURL();
		httpClient = new HttpClient(url);
		httpClient.sendGetRequest();
		result = httpClient.getResult();

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
		userId = "138";
		code = "1461895544";

		url.append("https://www.gigablast.com/search?q=");
		url.append(query);
		url.append("&format=");
		url.append(format);
		url.append("&userid=");
		url.append(userId);
		url.append("&code=");
		url.append(code);
	}
}