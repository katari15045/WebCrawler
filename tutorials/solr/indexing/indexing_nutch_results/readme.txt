Configuring Nutch
-----------------
1. $ vim $NUTCH_HOME/conf/solrindex-mapping.xml
2. $ Add the following
	<field dest="inlinks" source="inlinks"/>
	<field dest="outlinks" source="outlinks"/>
3. $ vim $NUTCH_HOME/conf/nutch-site.xml
4. In the property named plugin.includes the indexer should be solr not elasticSearch. The entire line looks as follows
	<value>protocol-http|urlfilter-regex|parse-(html|tika)|index-(basic|anchor)|indexer-solr|scoring-opic|urlnormalizer-(pass|regex|basic)</value>

configuring Solr
---------------
1. $ cd $SOLR_HOME
2. If temp_core exists then
	$ bin/solr delete -c temp_core
3. $ bin/solr create_core -c temp_core
4. bin/solr restart

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

Verdict
-------
Tested - but got only one result for http://explorelinux.github.io

------------------------------------------------------------------------------------------------------------------------------------------------

source : https://stackoverflow.com/questions/43757606/nutch-1-13-index-links-configuration
