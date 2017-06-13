package com.github.katari15045;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;


public class SolrSearchService 
{
	private String querystring;
	private String maxResultCount;
	private LinkedHashSet<SolrResult> solrResultSet;
	private Database database;
	private PreparedStatement preparedStatement;
	
	public LinkedHashSet<SolrResult> start(HttpServletRequest request) throws ClassNotFoundException, SQLException
	{
		getUserInput(request);
		fetchSolrResults(querystring, maxResultCount);
		storeSolrResultsinMySQL();
		
		return solrResultSet;
	}
	
	private void getUserInput(HttpServletRequest request)
	{
		querystring = request.getParameter("query");
		maxResultCount = request.getParameter("maxResultCount");
	}
	
	private String getParsedQueryString(String inpString)
	{
		StringBuilder result = new StringBuilder();
		int count = 0;

		for(String str:inpString.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		return result.toString();
	}
	
	private void fetchSolrResults(String inpQuery, String inpResultCount)
	{
		String parsedQuery = getParsedQueryString(inpQuery);
		StringBuilder url = new StringBuilder();
		url.append("http://localhost:8983/solr/webcrawler_core/select?q=");
		url.append(parsedQuery).append("&rows=").append(inpResultCount);

		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( url.toString(), "search_results_from_solr.xml" );

		SolrResultsParser solrResultsParser = new SolrResultsParser();
		solrResultSet = solrResultsParser.parse("search_results_from_solr.xml");
	}
	
	private void storeSolrResultsinMySQL() throws ClassNotFoundException, SQLException
	{
		initializeDatabase();
		
		Iterator<SolrResult> iterator = solrResultSet.iterator();
		
		while( iterator.hasNext() )
		{
			storeSolrResult( iterator.next() );
		}
		
		database.closeConnection();
	}
	
	private void initializeDatabase() throws ClassNotFoundException, SQLException
	{
		database = new Database();
		database.makeConnection();
		preparedStatement = database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS solr_results(title VARCHAR(5000) NOT NULL, url VARCHAR(3072) NOT NULL, content VARCHAR(3072) NOT NULL, PRIMARY KEY (url));");
		database.executeUpdate(preparedStatement);
	}
	
	private void storeSolrResult(SolrResult solrResult) throws SQLException
	{
		preparedStatement = database.getConnection().prepareStatement("INSERT INTO solr_results VALUES(?, ?, ?);");
		preparedStatement.setString(1, solrResult.getTitle());
		preparedStatement.setString(2, solrResult.getUrl());
		preparedStatement.setString(3, solrResult.getContent());
		
		database.executeUpdate(preparedStatement);
	}
}
