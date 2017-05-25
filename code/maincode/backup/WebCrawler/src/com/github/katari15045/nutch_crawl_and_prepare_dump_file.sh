sudo cp seed.txt /opt/apache-nutch-1.13/urls/
cd /opt/apache-nutch-1.13/
sudo rm -rf crawl
sudo -E bin/nutch inject crawl/crawldb urls/
sudo -E bin/nutch generate crawl/crawldb crawl/segments
sudo -E bin/nutch fetch crawl/segments/*
sudo -E bin/nutch parse crawl/segments/*
sudo -E bin/nutch updatedb crawl/crawldb crawl/segments/*
sudo -E bin/nutch invertlinks crawl/linkdb crawl/segments/*
rm -rf output
mkdir output
sudo -E bin/nutch readseg -dump crawl/segments/20*/ output -nocontent -nofetch - nogenerate -noparsedata -noparsetext