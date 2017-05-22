sudo cp api_results_for_solr.xml /opt/solr-6.5.1/indexFiles
cd /opt/solr-6.5.1/
sudo -u solr bin/solr create_core -c core_for_api_results
bin/post -c core_for_api_results indexFiles/