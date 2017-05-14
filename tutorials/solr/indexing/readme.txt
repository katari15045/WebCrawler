Indexing
--------
Don't do sudo -i
1. $ cd /opt/solr-6.5.1
2. $ sudo mkdir indexFiles
3. $ sudo cp example/exampledocs/money.xml indexFiles/
     You can also get this money.xml from here along with this readme.txt
4. $ sudo -u solr bin/solr create_core -c temp_core
     Newly created core can be seen at /var/solr/data
5. $ bin/post -c temp_core indexFiles/
     Now, the files present in indexFiles have been indexed

Searching
---------
1. Type http://localhost:8983/solr/temp_core/select?q=*:* on your browser
   This displays all the indexed data in temp_core
2. Type http://localhost:8983/solr/temp_core/select?q=america on your browser
   This displays the data which has America in it

Note : Same can be achieved from terminal using $ curl http://localhost:8983/solr/temp_core/select?q=america

