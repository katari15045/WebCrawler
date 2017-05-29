package com.github.katari15045;

public class NutchCrawlService 
{
	public void start()
	{
		
		Terminal terminal = new Terminal();
		terminal.start("nutch_crawl_and_prepare_dump_file.sh");
	}
}
