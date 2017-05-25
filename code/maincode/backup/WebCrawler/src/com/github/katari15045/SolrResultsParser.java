package com.github.katari15045;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.LinkedHashSet;

public class SolrResultsParser
{
	static
    {
        System.setProperty("jdk.xml.entityExpansionLimit", "0");
    }

    private StringBuilder parsedData;
    private SAXParserFactory spf;
    private SAXParser saxParser;
    private MyHandler handler;
    private LinkedHashSet<SolrResult> solrResultSet;

    public String parse(String file)
    {
    	parsedData = new StringBuilder();

    	try
        {
            spf = SAXParserFactory.newInstance();
            saxParser = spf.newSAXParser();
            handler = new MyHandler();
            solrResultSet = new LinkedHashSet<SolrResult>();
            saxParser.parse(file,handler);
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }

        return parsedData.toString();
    }

    public LinkedHashSet<SolrResult> getResults()
    {
        return solrResultSet;
    }

    private class MyHandler extends DefaultHandler
    {
        private boolean isTitle;
        private boolean isUrl;
        private String currentTitle;
        private String currentUrl;

        public MyHandler()
        {
            isTitle = false;
            isUrl = false;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
        {

            if( attributes.getValue(0) != null )
            {
                if( attributes.getValue(0).equals("name") )
                {
                    isTitle = true;
                }

                else if( attributes.getValue(0).equals("url") )
                {
                    isUrl = true;
                }
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
             if(isTitle)
             {
                currentTitle = deleteSpecialChars( new String(ch,start,length) );
             	isTitle = false;
             }

             else if(isUrl)
             {
                currentUrl = deleteSpecialChars( new String(ch,start,length) );
             	isUrl = false;

                SolrResult solrResult = new SolrResult();
                solrResult.setTitle(currentTitle);
                solrResult.setUrl(currentUrl);
                solrResultSet.add(solrResult);

                //crawlWithNutch();
             }
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

 
    }

    
}
