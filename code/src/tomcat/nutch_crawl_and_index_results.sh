# Pre-Requisites -> Solr is up and running, temp_core in Solr, urls directory in nutch, seed.txt

cp ~/seed.txt $NUTCH_HOME/urls
cd $NUTCH_HOME

if [ -d "crawl" ]
then
	rm -rf crawl
	echo "crawl directory removed!!!"
fi

echo "Nutch Crawl Starts"
bin/crawl -i -D solr.server.url=http://localhost:8983/solr/webcrawler_core urls/seed.txt crawl 2
echo "Nutch Crawl Ends"
