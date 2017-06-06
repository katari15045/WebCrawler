
1. $ cd $NUTCH_HOME
2. Keep a URL in $NUTCH_HOME/urls/seed.txt
3. Create a coe named temp_core in Solr
4. $ bin/crawl -i -D solr.server.url=http://localhost:8983/solr/temp_core urls/seed.txt crawl/crawldb 1
