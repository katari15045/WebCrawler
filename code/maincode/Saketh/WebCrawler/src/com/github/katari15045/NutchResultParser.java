package com.github.katari15045;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Iterator;


public class NutchResultParser
{
	private LinkedHashSet<String> links;
	private UrlDomainChecker urlDomainChecker;

	public LinkedHashSet<String> start()
	{
		try
		{
			FileReader fileReader = new FileReader("/opt/apache-nutch-1.13/output/dump");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			links = new LinkedHashSet<String>();
			urlDomainChecker = new UrlDomainChecker();

			while( line != null )
			{
				if( line.contains("URL::") )
				{
					processTheLink( line.substring(6, line.length()) );
				}

				line = bufferedReader.readLine();
			}

			fileReader.close();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

		return links;

	}

	private void processTheLink(String link)
	{
		Iterator<String> iterator = links.iterator();

		while( iterator.hasNext() )
		{
			if( urlDomainChecker.areTheyInSameDomain(link, (String)iterator.next()) )
			{
				return;
			}
		}

		links.add(link);
	}
}