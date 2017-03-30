
I Assume that you have already followed install_nutch.txt in the tutorial section.

1-> sudo -i
2-> cd /opt/apache-nutch-1.12/
3-> copy your seed.txt file(Contains 1 link in each line) and paste it in urls folder
--------------------------------------------------------------------------------------------------------------
4-> bin/nutch inject crawl/crawldb urls/
5-> bin/nutch generate crawl/crawldb crawl/segments -topN 10
6-> s1=crawl/segments/20170328220151/	(20170328220151 should be different in your case)
7-> echo $s1
8-> bin/nutch fetch $s1
9-> bin/nutch parse $s1
10-> bin/nutch updatedb crawl/crawldb $s1
11-> bin/nutch invertlinks crawl/linkdb -dir crawl/segments
12-> mkdir output
13-> bin/nutch readseg -dump crawl/segments/20170328220151/ output -nocontent -nofetch - nogenerate -noparse -noparsetext
14-> cd output
15-> cat dump | grep outlink

Reference - https://camilotejeiro.wordpress.com/2015/08/27/nutch1-quick-tutorial-learning-to-crawl/
Note ---> In command 5, -topN 10 indicates fetch a maximum of 10 links per url. If you don't mention any limit then it fetches all the links in an URL and takes lots of time.
