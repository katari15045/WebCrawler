rm $SOLR_HOME/indexFiles/*
cp parsed_nutch_crawl_results_for_solr.xml $SOLR_HOME/indexFiles
cd $SOLR_HOME
bin/post -c core_for_nutch_results indexFiles/