create core
------------
1. $cd /opt/solr-6.5.0
2. $sudo -u solr bin/solr create_core -c temp_core
3. You can check newly created core in /var/solr/data

delete core
-----------
1. $cd /opt/solr-6.5.0
2. $sudo -u solr bin/solr delete -c temp_core
	
