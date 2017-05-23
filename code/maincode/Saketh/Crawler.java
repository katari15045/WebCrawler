import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

public class Crawler
{
	private static LinkedHashSet<SolrResult> solrResultSet;
	private static String result;

	private static String userQueryString;
	private static Scanner scanner;

	private static String parsedQueryString;
	private static String format;
	private static String resultCount;
	private static String userid;
	private static String code;
	private static StringBuilder url;

	public static void main(String[] args) throws Exception
	{
		scanner = new Scanner(System.in);
		System.out.println("Modules : \n1.store API results in Solr\n2.Feeding Nutch with Urls from Solr\n");
		System.out.print("Module number -> ");
		int userModuleNum = scanner.nextInt();

		if( userModuleNum == 1 )
		{
			storeAPIResultsInSolr();
		}

		else if( userModuleNum == 2 )
		{
			crawlWithNutch();
		}
	}

	private static void storeAPIResultsInSolr()
	{
		System.out.println("\n\nModule to store API results in Solr.\n");
		prepareparsedQueryString();
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
		System.out.println("\n\nModule for Feeding Nutch with Urls from Solr\n");
		prepareparsedQueryString();

		StringBuilder url = new StringBuilder();
		url.append("http://localhost:8983/solr/core_for_api_results/select?q=");
		url.append(parsedQueryString).append("&rows=").append(resultCount);

		HttpClient httpClient = new HttpClient();
		httpClient.sendGetRequest( url.toString(), "api_search_results_from_solr.xml" );

		SolrResultsParser solrResultsParser = new SolrResultsParser();
		solrResultsParser.parse("api_search_results_from_solr.xml");
		solrResultSet = solrResultsParser.getResults();
	}

	private static void prepareUrl()
	{
		url = new StringBuilder();
		format = "xml";
		userid = "138";
		code  = "1461895544";
		url.append("https://www.gigablast.com/search?q=").append(parsedQueryString).append("&format=").append(format).append("&n=").append(resultCount)
			.append("&userid=").append(userid).append("&code=").append(code);
	}

	private static void takeUserInput()
	{
		System.out.print("Search for -> ");
		scanner.nextLine();
		userQueryString = scanner.nextLine();

		System.out.print("No.of results to be seen -> ");
		resultCount = String.valueOf( scanner.nextInt() ) ;
	}

	private static void prepareparsedQueryString()
	{
		StringBuilder result = new StringBuilder();

		takeUserInput();
		int count = 0;

		for(String str:userQueryString.split(" "))
		{
			if( count != 0 )
			{
				result.append("%20");
			}

			result.append(str);

			count = count + 1;
		}

		parsedQueryString = result.toString();
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
