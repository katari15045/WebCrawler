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

    private SAXParserFactory spf;
    private SAXParser saxParser;
    private MyHandler handler;
    private LinkedHashSet<SolrResult> solrResultSet;

    public LinkedHashSet<SolrResult> parse(String file)
    {
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

        return solrResultSet;
    }

    private class MyHandler extends DefaultHandler
    {
        private boolean isTitle;
        private boolean isUrl;
        private boolean isContent;
        private String currentTitle;
        private String currentUrl;
        private String currentContent;

        public MyHandler()
        {
            isTitle = false;
            isUrl = false;
            isContent = false;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
        {

            if( attributes.getValue(0) != null )
            {
                if( attributes.getValue(0).equals("title") )
                {
                    isTitle = true;
                }

                else if( attributes.getValue(0).equals("url") )
                {
                    isUrl = true;
                }
                
                else if( attributes.getValue(0).equals("content") )
                {
                    isContent = true;
                }
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
             if(isTitle)
             {
                currentTitle = new String(ch,start,length);
             	isTitle = false;
             }

             else if(isUrl)
             {
                currentUrl = new String(ch,start,length);
             	isUrl = false;
             }
             
             else if(isContent)
             {
            	 currentContent = new String(ch,start,length);
            	 
            	 if( currentContent.length() > currentTitle.length() )
            	 {
            		 if( currentTitle.equals( currentContent.substring(0, currentTitle.length() ) ) )
                	 {
                		 currentContent = currentContent.substring( currentTitle.length() ,currentContent.length() );
                	 }
            	 }
            	 
            	 if( currentContent.length() > 150 )
            	 {
            		 currentContent = currentContent.substring(0, 151);
            		 currentContent = currentContent + "...";
            	 }
            	 
            	 isContent = false;
            	 
            	 SolrResult solrResult = new SolrResult();
                 solrResult.setTitle(currentTitle);
                 solrResult.setUrl(currentUrl);
                 solrResult.setContent(currentContent);
                 solrResultSet.add(solrResult);
             }
        }
 
    }

    
}
