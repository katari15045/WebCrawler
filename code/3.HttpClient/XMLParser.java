import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;



public class XMLParser
{
	static
    {
        System.setProperty("jdk.xml.entityExpansionLimit", "0");
    }

    private StringBuilder parsedData;
    private SAXParserFactory spf;
    private SAXParser saxParser;
    private MyHandler handler;

    public String parse(String file)
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
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }

        return parsedData.toString();
    }

    private class MyHandler extends DefaultHandler
    {
        private boolean isTitle;
        private boolean isUrl;

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
             	parsedData.append("\t<doc>\n\t\t<field name=\"name\">").append( deleteSpecialChars( new String(ch,start,length) ) ).append("</field>\n");
             	isTitle = false;
             }

             else if(isUrl)
             {
             	parsedData.append("\t\t<field name=\"url\">").append( deleteSpecialChars( new String(ch,start,length) ) ).append("</field>\n\t</doc>\n\n");
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