if [ -d "$SOLR_HOME/indexFiles" ]
then
	echo "indexFiles exists"
	rm $SOLR_HOME/indexFiles/*
else
	echo "indexFiles doesn't exist"
	mkdir $SOLR_HOME/indexFiles
	echo "indexFiles created"
fi

cp ./tomcat/api_results_for_solr.xml $SOLR_HOME/indexFiles
echo "copied xml to indexFiles"
cd $SOLR_HOME
bin/post -c core_for_api_results indexFiles/