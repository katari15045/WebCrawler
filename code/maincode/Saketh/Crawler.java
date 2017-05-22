import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

public class Crawler
{
	private static LinkedHashSet<SolrResult> solrResultSet;
	private static String result;

	private static String queryString;
	private static String format;
	private static String resultCount;
	private static String userid;
	private static String code;
	private static StringBuilder url;

	public static void main(String[] args) throws Exception
	{
		
		storeAPIResultsInSolr();
		crawlWithNutch();
	}

	private static void storeAPIResultsInSolr()
	{
		prepareQueryString();
		prepareUrl();
	
		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( url.toString(), "api_data.xml" );

		APIResultParser apiResultParser = new APIResultParser();
		apiResultParser.parse("api_data.xml");

		Terminal terminal = new Terminal();
		terminal.start("store_api_results_in_solr.sh");
	}

	private static void crawlWithNutch()
	{
		fetchSolrResults();

		NutchFeeder nutchFeeder = new NutchFeeder();
		nutchFeeder.feed(solrResultSet, "parsed_nutch_crawl_results_for_solr.xml");

		Terminal terminal = new Terminal();
		terminal.start("store_nutch_results_in_solr.sh");	
	}

	private static void fetchSolrResults()
	{
		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( "http://localhost:8983/solr/core_for_api_results/select?q=*:*&rows=1", "api_search_results_from_solr.xml" );
		SolrResultsParser solrResultsParser = new SolrResultsParser();
		solrResultsParser.parse("api_search_results_from_solr.xml");
		solrResultSet = solrResultsParser.getResults();
	}

	private static void prepareUrl()
	{
		url = new StringBuilder();
		format = "xml";
		resultCount = "20";
		userid = "138";
		code  = "1461895544";
		url.append("https://www.gigablast.com/search?q=").append(queryString).append("&format=").append(format).append("&n=").append(resultCount)
			.append("&userid=").append(userid).append("&code=").append(code);
	}

	private static String takeUserInput()
	{
		Scanner scanner = new Scanner(System.in);
		String inpStr;
		System.out.print("Search for -> ");
		inpStr = scanner.nextLine();

		return inpStr;
	}

	private static void prepareQueryString()
	{
		StringBuilder result = new StringBuilder();

		String userStr = takeUserInput();
		int count = 0;

		for(String str:userStr.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		queryString = result.toString();
	}

	private static void writeDataToAFileForSolr(String data)
	{
		try
		{
			PrintWriter printWriter = new PrintWriter("solrIndexData.xml");
			printWriter.print(data);
			printWriter.close();
		}

		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
	}
}
