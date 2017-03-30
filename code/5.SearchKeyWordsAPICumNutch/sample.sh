

sudo cp seed.txt /opt/apache-nutch-1.12/urls/
cd /opt/apache-nutch-1.12/
sudo rm -rf crawl/crawldb
sudo rm -rf crawl/segments
sudo mkdir crawl/crawldb
sudo chmod 777 crawl/crawldb
sudo mkdir crawl/segments
sudo chmod 777 crawl/segments
bin/nutch inject crawl/crawldb urls/
bin/nutch generate crawl/crawldb crawl/segments -topN 10
bin/nutch fetch crawl/segments/20*
bin/nutch parse crawl/segments/20*
bin/nutch updatedb crawl/crawldb crawl/segments/20*
bin/nutch invertlinks crawl/linkdb -dir crawl/segments
rm -rf output
mkdir output
bin/nutch readseg -dump crawl/segments/20*/ output -nocontent -nofetch - nogenerate -noparse -noparsetext
cat output/dump | grep outlink