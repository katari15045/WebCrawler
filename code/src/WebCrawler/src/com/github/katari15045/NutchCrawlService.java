package com.github.katari15045;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NutchCrawlService 
{
	private Database database;
	
	public void start(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException
	{	
		Terminal terminal = new Terminal();
		terminal.start("nutch_crawl_and_index_results.sh");
		
		updateCrawlStatusOfCrawledUrls();
	}
	
	private void updateCrawlStatusOfCrawledUrls() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException
	{
		initializeDatabase();
		
		FileReader fileReader = new FileReader("seed.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String currentUrl = bufferedReader.readLine();

		while( currentUrl != null )
		{
			updateCrawlStatus(currentUrl);
			currentUrl = bufferedReader.readLine();
		}

		fileReader.close();
		bufferedReader.close();
	}
	
	private void initializeDatabase() throws ClassNotFoundException, SQLException
	{
		database = new Database();
		database.makeConnection();
	}
	
	private void updateCrawlStatus(String url) throws SQLException
	{
		PreparedStatement preparedStatement = database.getConnection().prepareStatement("UPDATE api_results SET crawl_status=? WHERE url=?;");
		preparedStatement.setInt(1, 1);
		preparedStatement.setString(2, url);
		
		database.executeUpdate(preparedStatement);
	}
}
