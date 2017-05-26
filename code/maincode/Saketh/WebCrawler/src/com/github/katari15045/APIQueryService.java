package com.github.katari15045;

import java.io.IOException;

public class APIQueryService 
{
	private String userQueryString;
	private int resultCount;
	
	private StringBuilder url;
	private String userid;
	private String code;
	private String format;
	
	private String parsedQueryString;
	private APIResult apiResult;
	
	
	public APIResult start(String inpQuery, int inpCount) throws IOException
	{
		userQueryString = inpQuery;
		resultCount = inpCount;
		storeAPIResultsInSolr();
		
		return apiResult;
	}
	
	private void storeAPIResultsInSolr() throws IOException
	{
		prepareparsedQueryString();
		prepareUrl();
	
		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( url.toString(), "api_data.xml" );

		APIResultParser apiResultParser = new APIResultParser();
		apiResult = apiResultParser.parse("api_data.xml");

		Terminal terminal = new Terminal();
		terminal.start("store_api_results_in_solr.sh");
	}
	
	private void prepareparsedQueryString()
	{
		StringBuilder result = new StringBuilder();
		int count = 0;

		for(String str:userQueryString.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		parsedQueryString = result.toString();
	}
	
	private void prepareUrl()
	{
		url = new StringBuilder();
		format = "xml";
		userid = "138";
		code  = "1461895544";
		url.append("https://www.gigablast.com/search?q=").append(parsedQueryString).append("&format=").append(format).append("&n=").append(resultCount)
			.append("&userid=").append(userid).append("&code=").append(code);
	}
}
