package com.github.katari15045;

import java.lang.Exception;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient
{
	private URL url;
	private HttpURLConnection connection;
	

	public void sendGetRequest(String urlString, String file) 
	{
		try
		{
			url = new URL(urlString);
			makeConnection();
			witeDataToAFile(file);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void makeConnection() throws IOException, ProtocolException
	{
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
	}

	private void witeDataToAFile(String inpFileName) throws IOException
	{
		String line;
		StringBuilder result = new StringBuilder();
		StringBuilder path = new StringBuilder();
		
		path.append( System.getProperty("user.dir") ).append("/").append(inpFileName);
		PrintWriter pr = new PrintWriter(path.toString());
		
		BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
		line = br.readLine();

		while( line != null )
		{
			result.append(line);
			line = br.readLine();
		}
		
		pr.print(result);
		pr.flush();
		pr.close();
	}
}