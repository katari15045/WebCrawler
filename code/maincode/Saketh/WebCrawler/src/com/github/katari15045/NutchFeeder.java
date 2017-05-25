package com.github.katari15045;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileNotFoundException;


public class NutchFeeder
{
	private LinkedHashSet<SolrResult> solrResultSet;
	private StringBuilder parsedSolrDataFromNutch;

	public void feed(LinkedHashSet<SolrResult> inpSet, String inpFile)
	{
		solrResultSet = inpSet;
		Iterator<SolrResult> iterator = solrResultSet.iterator();
		SolrResult solrResultForNutch;
		parsedSolrDataFromNutch = new StringBuilder();
		String currentTitle, currentUrl;
		parsedSolrDataFromNutch.append("<add>\n");

		while( iterator.hasNext() )
		{
			solrResultForNutch = (SolrResult) iterator.next();
			currentTitle = deleteSpecialChars( solrResultForNutch.getTitle() );
			currentUrl = deleteSpecialChars( solrResultForNutch.getUrl() );
			parsedSolrDataFromNutch.append("\t<doc>\n\t\t<field name=\"name\">").append(currentTitle).append("</field>\n");
			parsedSolrDataFromNutch.append("\t\t<field name=\"url\">").append(currentUrl).append("</field>\n\t</doc>\n\n");
			crawlWithNutch(currentTitle, currentUrl);
		}

		parsedSolrDataFromNutch.append("</add>\n");
		writeParsedNutchResultsToAFile(inpFile);
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

    private void crawlWithNutch(String currentTitle, String currentUrl)
    {
        storeLinkInAFile(currentUrl);
        Terminal Terminal = new Terminal();
        Terminal.start("nutch_crawl_and_prepare_dump_file.sh");
        NutchResultParser nutchResultParser = new NutchResultParser();
        LinkedHashSet<String> nutchOutputUrls = nutchResultParser.start();
        storeCrawledUrls(currentTitle, nutchOutputUrls);
    }

    private void storeLinkInAFile(String inpUrl)
    {

        if( !inpUrl.substring(0, 4).equals("http") )
        {
            inpUrl = "http://" + inpUrl;
        }

        try
        {
            PrintWriter printWriter = new PrintWriter("seed.txt");
            printWriter.print(inpUrl);
            printWriter.close();    
        }

        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void storeCrawledUrls(String currentTitle, LinkedHashSet<String> urls)
    {
        Iterator<String> iterator = urls.iterator();

        while( iterator.hasNext() )
        {
            parsedSolrDataFromNutch.append("\t<doc>\n\t\t<field name=\"name\">").append(currentTitle).append("</field>\n");
            parsedSolrDataFromNutch.append("\t\t<field name=\"url\">").append( iterator.next() ).append("</field>\n\t</doc>\n\n");
        }
    }

    private void writeParsedNutchResultsToAFile(String inpFile)
    {
    	try
    	{
    		PrintWriter printWriter = new PrintWriter(inpFile);
    		printWriter.print( parsedSolrDataFromNutch.toString() );
    		printWriter.close();
    	}

    	catch(FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    }
}
