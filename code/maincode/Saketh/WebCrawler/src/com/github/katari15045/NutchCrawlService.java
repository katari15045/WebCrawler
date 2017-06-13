package com.github.katari15045;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NutchCrawlService 
{
	private String[] crawledUrls;
	private Database database;
	
	public void start(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException
	{	
		Terminal terminal = new Terminal();
		terminal.start("nutch_crawl_and_index_results.sh");
		
		updateCrawlStatusOfCrawledUrls(request);
	}
	
	private void updateCrawlStatusOfCrawledUrls(HttpServletRequest request) throws ClassNotFoundException, SQLException
	{
		initializeDatabase();
		getCrawledUrls(request);
		
		for (String url : crawledUrls) 
		{
			updateCrawlStatus(url);
		}
	}
	
	private void initializeDatabase() throws ClassNotFoundException, SQLException
	{
		database = new Database();
		database.makeConnection();
	}
	
	private void getCrawledUrls(HttpServletRequest request)
	{
		ServletContext servletContext = request.getServletContext();
		crawledUrls = (String[]) servletContext.getAttribute("selectedUrls");
	}
	
	private void updateCrawlStatus(String url) throws SQLException
	{
		PreparedStatement preparedStatement = database.getConnection().prepareStatement("UPDATE api_results SET crawl_status=? WHERE url=?;");
		preparedStatement.setInt(1, 1);
		preparedStatement.setString(2, url);
		
		database.executeUpdate(preparedStatement);
	}
}
