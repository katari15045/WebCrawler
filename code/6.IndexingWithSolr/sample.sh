cd /opt/solr-6.4.2/
bin/solr restart
rm -rf server/solr/temp_core
bin/solr create -c temp_core
cd server/solr/temp_core/conf
mv managed-schema managed-schema.backup
mv solrconfig.xml solrconfig.xml.backup
cp /home/saketh/Downloads/schema.xml .
cp /home/saketh/Downloads/solrconfig.xml .
cd /opt/solr-6.4.2/
bin/solr restart
cd /opt/apache-nutch-1.12/
s1=crawl/segments/2*
bin/nutch solrindex http://localhost:8983/solr/temp_core crawl/crawldb/ -linkdb crawl/linkdb/ $s1
curl "http://localhost:8983/solr/temp_core/select?q=redbus"
