package com.github.katari15045;

public class SolrResult
{
	private String title;
	private String url;
	private String content;

	public void setTitle(String inpTitle)
	{
		title = inpTitle;
	}

	public void setUrl(String inpUrl)
	{
		url = inpUrl;
	}
	
	public void setContent(String inpContent)
	{
		content = inpContent;
	}

	public String getTitle()
	{
		return title;
	}

	public String getUrl()
	{
		return url;
	}
	
	public String getContent()
	{
		return content;
	}
}