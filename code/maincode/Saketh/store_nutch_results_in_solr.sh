sudo cp parsed_nutch_crawl_results_for_solr.xml /opt/solr-6.5.1/indexFiles
cd /opt/solr-6.5.1/
sudo -u solr bin/solr create_core -c core_for_nutch_results
bin/post -c core_for_nutch_results indexFiles/