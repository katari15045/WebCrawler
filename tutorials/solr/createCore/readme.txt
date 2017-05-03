1. $cd /opt/solr-6.5.0
2. $mkdir cores;cd cores;mkdir temp_core;cd ../
3. Make sure you have "elevate.xml  protwords.txt  schema.xml  solrconfig.xml  stopwords.txt  synonyms.txt" in cores/temp_core
4. sudo -u solr bin/solr create_core -c temp_core -d cores/temp_core/
	If you get any error like temp_core already exists then 
	$sudo rm -rf /var/solr/data/temp_core/
	$sudo -u solr bin/solr delete -c temp_core
5.After successful core creation you may see
	Copying configuration to new core instance directory:
	/var/solr/data/temp_core

	Creating new core 'temp_core' using command:
	http://localhost:8983/solr/admin/cores?action=CREATE&name=temp_core&instanceDir=temp_core
