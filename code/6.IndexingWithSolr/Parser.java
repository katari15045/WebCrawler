import java.util.LinkedHashSet;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Parser
{
	private LinkedHashSet<String> urlSet;

    static
    {
        System.setProperty("jdk.xml.entityExpansionLimit", "0");
    }

	public Parser()
	{
		urlSet = new LinkedHashSet<String>();

        try
        {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse("sample.xml",handler);
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }
	}

    public LinkedHashSet<String> getUrls()
    {
        return urlSet;
    }
    
    private class MyHandler extends DefaultHandler
    {
        private boolean isUrl;

        public MyHandler()
        {
            isUrl = false;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
        {
            if (qName.equals("str")) 
            {
                if(  attributes.getValue("name").equals("id") )
                {
                    isUrl = true;
                }           
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
            if(isUrl)
            {
                urlSet.add( new String(ch, start, length) );
                isUrl = false;
            }  
        }
    }
}
