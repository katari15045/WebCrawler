package com.github.katari15045;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class NutchFeederService 
{
	private ServletContext servletContext;
	
	private boolean crawlNowStatus;
	private LinkedHashSet<String> unrefinedTitleSet;
	private LinkedHashSet<String> unrefinedURLSet;
	private String selectedIndices[];
	
	private Iterator<String> titleIterator;
	private Iterator<String> urlIterator;
	
	private StringBuilder parsedSolrData;
	private StringBuilder parsedNutchData;
	
	public void start(HttpServletRequest request) throws FileNotFoundException
	{
		getDataFromRequest(request);
		initializeObjects();	
		prepareNutchUrlsAndSolrAPIData();
		storeSolrData("api_results_for_solr.xml");
		storeNutchData();
	}
	
	private void getDataFromRequest(HttpServletRequest request)
	{
		String tempString = request.getParameter("crawlNowStatus");
		
		if( tempString.equals("crawlNow") )
		{
			crawlNowStatus = true;
		}
		
		else if( tempString.equals("crawlLater") )
		{
			crawlNowStatus = false;
		}
		
		selectedIndices = request.getParameterValues("id");
		servletContext = request.getServletContext();
		unrefinedTitleSet = (LinkedHashSet<String>) servletContext.getAttribute("unrefinedAPITitleSet");
		unrefinedURLSet= (LinkedHashSet<String>) servletContext.getAttribute("unrefinedAPIURLSet");
	}
	
	private void initializeObjects()
	{
		titleIterator = unrefinedTitleSet.iterator();
		urlIterator = unrefinedURLSet.iterator();
		parsedSolrData = new StringBuilder();
		parsedNutchData = new StringBuilder();
		parsedSolrData.append("<add>\n");
	}
	
	private void prepareNutchUrlsAndSolrAPIData()
	{
		int currentIndex = 0;
		int currentSelectedIndex = 0;
		int currentSelectedValue = Integer.parseInt( selectedIndices[currentSelectedIndex] );
		
		while( titleIterator.hasNext() )
		{
			SolrAPINode newNode = new SolrAPINode();
			newNode.setTitle( deleteSpecialChars(titleIterator.next() ) );
			newNode.setUrl( deleteSpecialChars(urlIterator.next() ) );
			
			if( currentSelectedValue == currentIndex )
			{
				newNode.setCrawlStatus(true);
				currentSelectedIndex = currentSelectedIndex + 1;
				currentSelectedValue = Integer.parseInt( selectedIndices[currentSelectedIndex] );
				
				parsedNutchData.append( standardizeURLWithProtocol( newNode.getUrl() ) ).append("\n");
			}
			
			else
			{
				newNode.setCrawlStatus(false);
			}
			
			parseNodeForSolr(newNode);
			currentIndex = currentIndex + 1;
		}
		
		parsedSolrData.append("</add>\n");
	}
	
	private void storeSolrData(String inpFile) throws FileNotFoundException
	{
		StringBuilder path = new StringBuilder();
		path.append( System.getProperty("user.dir") ).append("/tomcat/").append(inpFile);
		
		PrintWriter printWriter = new PrintWriter( path.toString() );
		printWriter.print(parsedSolrData);
		printWriter.close();
		
		Terminal terminal = new Terminal();
		terminal.start("store_api_results_in_solr.sh");
	}
	
	private void parseNodeForSolr(SolrAPINode solrAPINode)
	{
		parsedSolrData.append("\t<doc>\n\t\t<field name=\"name\">").append( solrAPINode.getTitle() ).append("</field>\n")
		.append("\t\t<field name=\"url\">").append( solrAPINode.getUrl() ).append("</field>\n" )
		.append("\t\t<field name=\"crawl_status\">").append( String.valueOf( solrAPINode.getCrawlStatus() ) )
		.append("</field>\n\t</doc>\n\n");
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
