package com.github.katari15045;

import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;

public class SolrSearchService 
{
	private String querystring;
	private String maxResultCount;
	private LinkedHashSet<SolrResult> solrResultSet;
	
	public LinkedHashSet<SolrResult> start(HttpServletRequest request)
	{
		getUserInput(request);
		fetchSolrResults(querystring, maxResultCount);
		
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
}
