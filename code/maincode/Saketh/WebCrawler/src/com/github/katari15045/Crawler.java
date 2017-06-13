package com.github.katari15045;

import java.util.Scanner;
import java.util.LinkedHashSet;

public class Crawler
{
	private static LinkedHashSet<SolrResult> solrResultSet;

	private static String userQueryString;
	private static Scanner scanner;

	private static String parsedQueryString;
	private static String resultCount;
/*
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
*/
	
	private static void crawlWithNutch()
	{
		fetchSolrResults();

		NutchFeeder nutchFeeder = new NutchFeeder();
		nutchFeeder.feed(solrResultSet, "parsed_nutch_crawl_results_for_solr.xml");

		Terminal terminal = new Terminal();
		terminal.start("store_nutch_results_in_solr.sh");	
	}

	
	
}
