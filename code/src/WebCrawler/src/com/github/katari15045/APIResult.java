package com.github.katari15045;

import java.util.LinkedHashSet;

public class APIResult 
{
	private LinkedHashSet<String> titleSet;
	private LinkedHashSet<String> urlSet;
	
	public LinkedHashSet<String> getTitleSet() 
	{
		return titleSet;
	}
	
	public void setTitleSet(LinkedHashSet<String> titleSet) 
	{
		this.titleSet = titleSet;
	}
	
	public LinkedHashSet<String> getUrlSet() 
	{
		return urlSet;
	}
	
	public void setUrlSet(LinkedHashSet<String> urlSet) 
	{
		this.urlSet = urlSet;
	}
	
	
}
