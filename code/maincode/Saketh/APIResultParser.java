import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;



public class APIResultParser
{
	static
    {
        System.setProperty("jdk.xml.entityExpansionLimit", "0");
    }

    private StringBuilder parsedData;
    private SAXParserFactory spf;
    private SAXParser saxParser;
    private MyHandler handler;

    public void parse(String file)
    {
    	parsedData = new StringBuilder();

    	try
        {
            spf = SAXParserFactory.newInstance();
            saxParser = spf.newSAXParser();
            handler = new MyHandler();
            parsedData.append("<add>\n");
            saxParser.parse(file,handler);
            parsedData.append("</add>\n");
            writeToAFile();
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }

    }

    private void writeToAFile()
    {
        try
        {
            PrintWriter printWriter = new PrintWriter("api_results_for_solr.xml");
            printWriter.print( parsedData.toString() );
            printWriter.close();
        }

        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
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
            if( qName.equals("title") )
            {
            	isTitle = true;
            }

            else if( qName.equals("url") )
            {
            	isUrl = true;
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
             if(isTitle)
             {
                currentTitle = deleteSpecialChars( new String(ch,start,length) );
             	parsedData.append("\t<doc>\n\t\t<field name=\"name\">").append(currentTitle).append("</field>\n");
             	isTitle = false;
             }

             else if(isUrl)
             {
                currentUrl = deleteSpecialChars( new String(ch,start,length) );
                System.out.println(" -------> " + currentUrl);
             	parsedData.append("\t\t<field name=\"url\">").append(currentUrl).append("</field>\n\t</doc>\n\n");
             	isUrl = false;
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