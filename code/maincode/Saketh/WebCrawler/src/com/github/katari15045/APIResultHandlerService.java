package com.github.katari15045;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class APIResultHandlerService 
{
	private ServletContext servletContext;
	
	private LinkedHashSet<String> unrefinedTitleSet;
	private LinkedHashSet<String> unrefinedURLSet;
	private String selectedIndices[];
	
	private Iterator<String> titleIterator;
	private Iterator<String> urlIterator;
	
	private StringBuilder parsedNutchData;
	private Database database;
	private PreparedStatement preparedStatement;
	
	
	public String[] start(HttpServletRequest request) throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		getDataFromRequest(request);
		initializeObjects();	
		prepareNutchUrlsAndStoreAPIResultsInMySQL();
		storeNutchData("seed.txt");
		
		return selectedIndices;
	}
	
	private void getDataFromRequest(HttpServletRequest request)
	{
		selectedIndices = request.getParameterValues("id");
		
		servletContext = request.getServletContext();
		APIResult apiResult = (APIResult) servletContext.getAttribute("apiResults");
		
		unrefinedTitleSet = apiResult.getTitleSet();
		unrefinedURLSet= apiResult.getUrlSet();
	}
	
	private void initializeObjects() throws ClassNotFoundException, SQLException
	{
		titleIterator = unrefinedTitleSet.iterator();
		urlIterator = unrefinedURLSet.iterator();
		parsedNutchData = new StringBuilder();
		database = new Database();
		
		database.makeConnection();
		preparedStatement = database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS api_results(title VARCHAR(5000) NOT NULL, url VARCHAR(3072) NOT NULL, crawl_status INT NOT NULL, PRIMARY KEY (url));");
		database.executeUpdate(preparedStatement);
	}
	
	private void prepareNutchUrlsAndStoreAPIResultsInMySQL() throws SQLException
	{
		int currentIndex = 0, currentSelectedIndex = 0;
		int currentSelectedValue = Integer.parseInt( selectedIndices[currentSelectedIndex] );
		String currentUrl, currentTitle;
		int currentCrawlStatus;
		
		while( titleIterator.hasNext() )
		{	
			currentTitle = deleteSpecialChars(titleIterator.next() );
			currentUrl = deleteSpecialChars(urlIterator.next() );
			
			if( currentSelectedValue == currentIndex )
			{
				currentCrawlStatus = 0;
				currentSelectedIndex = currentSelectedIndex + 1;
				
				if( currentSelectedIndex < selectedIndices.length )
				{
					currentSelectedValue = Integer.parseInt( selectedIndices[currentSelectedIndex] );
				}
				
				parsedNutchData.append( standardizeURLWithProtocol(currentUrl) ).append("\n");
			}
			
			else
			{
				currentCrawlStatus = -1;
			}
			
			storeInMySQL(currentTitle, currentUrl, currentCrawlStatus);
			currentIndex = currentIndex + 1;
		}
	}
	
	private void storeInMySQL(String title, String url, int crawlStatus) throws SQLException
	{
		preparedStatement = database.getConnection().prepareStatement("INSERT INTO api_results values(?, ?, ?);");
		preparedStatement.setString(1, title);
		preparedStatement.setString(2, url);
		preparedStatement.setInt(3, crawlStatus);
		
		database.executeUpdate(preparedStatement);
	}
	
	private void storeNutchData(String inpFile) throws FileNotFoundException
	{
		StringBuilder path = new StringBuilder();
		path.append( System.getProperty("user.dir") ).append("/").append(inpFile);
		
		PrintWriter printWriter = new PrintWriter( path.toString() );
		printWriter.print(parsedNutchData);
		printWriter.close();
	}
	
	private String deleteSpecialChars(String inpString)
    {
    	StringBuilder stringBuilder = new StringBuilder(inpString);
    	int index = stringBuilder.indexOf("&");

    	while( index >= 0 )
    	{
    		stringBuilder.deleteCharAt(index);
    		index = stringBuilder.indexOf("&");
    	}

    	return stringBuilder.toString();
    }
	
	private String standardizeURLWithProtocol(String inpUrl)
	{
		if( !inpUrl.substring(0, 4).equals("http") )
        {
            inpUrl = "http://" + inpUrl;
        }
		
		return inpUrl;
	}
}
