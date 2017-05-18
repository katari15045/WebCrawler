import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Sample 
{
	private static Document document;
	private static Elements links;
	private static String targetURL;
	
	public static void main(String[] args)
	{
		extractDocument();
		extractLinks();
		printLinks();
	}
	
	private static void extractDocument()
	{
		document = null;
		
		try
		{
			targetURL = "http://explorelinux.github.io/index.html";
			document = Jsoup.connect(targetURL).get();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void extractLinks()
	{
		links = document.select("a");
	}
	
	private static void printLinks()
	{
		for(Element e:links )
		{
			System.out.println( e.attr("abs:href") );
		}
	}
}

