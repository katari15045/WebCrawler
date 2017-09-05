package com.github.katari15045;

import java.io.IOException;

public class APIQueryService 
{
	private String query;
	private int resultCount;
	
	private StringBuilder url;
	private String userid;
	private String code;
	private String format;
	
	private String parsedQuery;
	private APIResult apiResult;
	
	
	public APIResult start(String inpQuery, int inpCount) throws IOException
	{
		query = inpQuery;
		resultCount = inpCount;
		
		prepareparsedQueryString();
		prepareUrl();
	
		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( url.toString(), "api_data.xml" );

		APIResultParser apiResultParser = new APIResultParser();
		apiResult = apiResultParser.parse("api_data.xml");
		
		return apiResult;
	}
	
	private void prepareparsedQueryString()
	{
		StringBuilder result = new StringBuilder();
		int count = 0;

		for(String str:query.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		parsedQuery = result.toString();
	}
	
	private void prepareUrl()
	{
		url = new StringBuilder();
		format = "xml";
		userid = "138";
		code  = "1461895544";
		url.append("https://www.gigablast.com/search?q=").append(parsedQuery).append("&format=").append(format).append("&n=").append(resultCount)
			.append("&userid=").append(userid).append("&code=").append(code);
	}
}
