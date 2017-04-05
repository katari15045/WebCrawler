cd /opt/solr-6.4.2/
bin/solr restart
bin/solr create -c temp_core
cd server/solr/temp_core/conf
mv managed-schema managed-schema.backup
mv solrconfig.xml solrconfig.xml.backup
cp <path to schema.xml> .
cp <path to solrconfig.xml> .
cd /opt/solr-6.4.2/
bin/solr restart
cd /opt/apache-nutch-1.12/
s1=crawl/segments/20170330230701/
bin/nutch solrindex http://localhost:8983/solr/temp_core crawl/crawldb/ -linkdb crawl/linkdb/ $s1
type http://localhost:8983/solr/foo/select?q=$querystring on browser
or type curl "http://localhost:8983/solr/temp_core/select?q=lemur" on terminal
