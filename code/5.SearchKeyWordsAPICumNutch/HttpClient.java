import java.lang.Exception;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;

public class HttpClient
{
	private URL url;
	private String urlString;
	private int resultCount;
	private HttpURLConnection connection;
	private BufferedReader br;
	private LinkedHashSet<String> linkedHashSet;

	public HttpClient(StringBuilder inpURL, int inpResultCount)
	{
		urlString = inpURL.toString();
		resultCount = inpResultCount;
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
		linkedHashSet = new LinkedHashSet<String>(resultCount);
		url = new URL(urlString);
	}

	private void makeConnection() throws IOException, ProtocolException
	{
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
	}

	private void readResponse() throws IOException
	{
		String line, tempString;

		br = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
		line = br.readLine();

		while( line != null )
		{

			if( line.contains("<url>") )
			{
				System.out.print(line);
				tempString = line.substring(16, line.indexOf("]]>"));

				if( !tempString.substring(0, 4).equals("http") )
				{
					tempString = "http://" + tempString;
				}

				linkedHashSet.add(tempString);
			}

			line = br.readLine();
		}
	}

	public LinkedHashSet<String> getResult()
	{
		return linkedHashSet;
	}
}