import java.io.*;
import java.net.*;

public class sample
{
	private static StringBuilder result;
	private static URL url;
	private static HttpURLConnection connection;
	private static BufferedReader br;
	private static String line;

	public static void main(String[] args) throws Exception
	{
		result = new StringBuilder();
		url = new URL("https://www.gigablast.com/search?q=test&format=xml&userid=12345&code=demo");
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		br = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
		line = br.readLine();

		while( line != null )
		{
			result.append(line);
			line = br.readLine();
		}

		br.close();

		System.out.println(result);
	}
}