import java.lang.Exception;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient
{
	private URL url;
	private String urlString;
	private HttpURLConnection connection;
	private BufferedReader br;
	private String line;
	private StringBuilder result;

	public HttpClient(String inpURL)
	{
		urlString = inpURL;
	}

	public void sendGetRequest() 
	{
		try
		{
			initializeObjects();
			makeConnection();
			readResponse();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initializeObjects() throws MalformedURLException
	{
		result = new StringBuilder();
		url = new URL(urlString);
		System.out.println(urlString);
	}

	private void makeConnection() throws IOException, ProtocolException
	{
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
	}

	private void readResponse() throws IOException
	{
		br = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
		line = br.readLine();

		while( line != null )
		{
			if( line.contains("<url>") )
			{
				String tempString = line.substring(16, line.indexOf("]]>"));

				if( !tempString.substring(0, 4).equals("http") )
				{
					tempString = "http://" + tempString;
				}

				System.out.println(tempString);
			}

			else if( line.contains("<title>") )
			{
				String tempString = line.substring(18, line.indexOf("]]>"));
				System.out.println(tempString);
			}

			result.append(line).append("\n");
			line = br.readLine();
		}
	}

	public String getResult()
	{
		return result.toString();
	}
}