1.create a core named temp_core
2.$ sudo -i
3.$ cd /opt/apache-nutch-1.13
4.$ bin/nutch solrindex http://localhost:8983/solr/temp_core crawl/crawldb/ -linkdb crawl/linkdb/ crawl/segments
5. type localhost:8983/solr/temp_core/select?q=$github in browser
   or type curl localhost:8983/solr/temp_core/select?q=$github
