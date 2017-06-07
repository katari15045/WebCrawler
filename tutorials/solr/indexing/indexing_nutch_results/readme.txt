Indexing Nutch Results
---------------------
1. $ cd $NUTCH_HOME
2. $ bin/nutch index -D solr.server.url=http://localhost:8983/solr/temp_core crawl/crawldb/ -linkdb crawl/linkdb/ crawl/segments/20170606201852/ -filter -normalize -deleteGone
3. The output looks as follows

Segment dir is complete: crawl/segments/20170606201852.
Indexer: starting at 2017-06-06 21:43:45
Indexer: deleting gone documents: true
Indexer: URL filtering: true
Indexer: URL normalizing: true
Active IndexWriters :
SOLRIndexWriter
	solr.server.url : URL of the SOLR instance
	solr.zookeeper.hosts : URL of the Zookeeper quorum
	solr.commit.size : buffer size when sending to SOLR (default 1000)
	solr.mapping.file : name of the mapping file for fields (default solrindex-mapping.xml)
	solr.auth : use authentication (default false)
	solr.auth.username : username for authentication
	solr.auth.password : password for authentication


Indexing 1/1 documents
Deleting 0 documents
Indexer: number of documents indexed, deleted, or skipped:
Indexer:      1  indexed (add/update)
Indexer: finished at 2017-06-06 21:43:49, elapsed: 00:00:03

Searching
---------
Type http://localhost:8983/solr/temp_core/select?q=*:* on your browser

Getting just meta_data of seed url but not the metadata of each seed url
------------------------------------------------------------------------
1. You need to increase the number of rounds Nutch crawls to 2 as by default it is set to 1
2. Where to increase it? -> Search for that in $NUTCH_HOME/nutch-site.xml (or) pass it as argument in one of the commands(inject, generate, fetch, parse, updatedb, invertlinks, index) (or) try understanding -topN argument

------------------------------------------------------------------------------------------------------------------------------------------------

source : https://stackoverflow.com/questions/43757606/nutch-1-13-index-links-configuration
