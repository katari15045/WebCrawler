create core
------------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ sudo -u solr bin/solr create_core -c temp_core
   If this doesn't work then you can try $ bin/solr create_core -c temp_core
3. You can check newly created core in /var/solr/data/tem_core

delete core
-----------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ bin/solr delete -c temp_core
	
