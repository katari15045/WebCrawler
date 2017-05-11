create core
------------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ sudo bin/solr create_core -c temp_core -force
3. You can check newly created core in /opt/solr-6.5.1/server/solr
4. $ cd /opt/solr-6.5.1/server/solr/temp_core/conf
5. Backup old schema file with $ mv sudo mv managed-schema managed-schema.backup
6. Copy the schema file of Nutch to temp_core using $ sudo cp /opt/apache-nutch-1.13/conf/schema.xml .
7. $ cd /opt/solr-6.5.1/
8. Restart Solr using $ sudo bin/solr restart -force

delete core
-----------
Don't do $sudo -i
1. $ cd /opt/solr-6.5.0
2. $ bin/solr delete -c temp_core
	
