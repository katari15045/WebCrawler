Pre-Requisite
--------------
Nutch & Solr has been configured
FYI : You can refer configuration folder to configure them


Crawling & Indexing
--------------------

1. $ cd $NUTCH_HOME
2. Keep a URL in $NUTCH_HOME/urls/seed.txt
3. Create a core named temp_core in Solr
4. $ bin/crawl -i -D solr.server.url=http://localhost:8983/solr/temp_core urls/seed.txt crawl 2

Note : Here 2 means num_rounds. If num_rounds is 1 then meta_data('outlinks-without their meta_data', title of seed urls, content of seed urls etc) of seed urls is extracted
	If num_rounds is 2 then the same content when 'num_rouns is 1' is extracted along with this, each outlink of the above result is treated as a seed url and Nutch crawls over all the outlinks i.e you get the meta_data(title, content, 'their outlinks without metadata') of each outlink



Searching
---------

Type http://localhost:8983/solr/temp_core/select?q=*:* on your browser
