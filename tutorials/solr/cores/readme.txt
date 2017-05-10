create core
------------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ sudo bin/solr create_core -c temp_core -force
3. You can check newly created core in /opt/solr-6.5.1/server/solr

delete core
-----------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ bin/solr delete -c temp_core
	
