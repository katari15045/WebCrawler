Intro
-----
Solr Indexer is used instead of elastic search. 
Links(Outlinks) are also indexed in Solr.
Upper limit of some properties is relaxed

Configuration
-------------
1. Open $NUTCH_HOME/conf/nutch-site.xml in sublime text
2. Search for the property whose name is plugin.includes


Indexer
------
In the 'value' tags the 'indexer' should be 'solr' & not 'elasticSearch'

Links
-----
In the 'value' tags add 'links' to 'index'

With the above 2 changes it should look like

<value>protocol-http|urlfilter-regex|parse-(html|tika)|index-(basic|anchor|links)|indexer-solr|scoring-opic|urlnormalizer-(pass|regex|basic)</value>

Limit
-----
1. In the same file search for limit
2. Make -1 as the value for file.content.limit, http.content.limit & ftp.content.limit
	<value>-1</value>
