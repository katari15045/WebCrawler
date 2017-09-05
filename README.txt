For source code, move to code/src/WebCrawler/src/com/github/katari15045

Features of this search engine (WebCrawler):
-> User can enter some keywords and results (Title, content etc of each result) are displayed

How does it work?
There are 2 phases namely indexing & searching.

Indexing (Takes lots of time)
--------
-> For some 'key words (dependent on the domain for which you are developing the search engine)', seed URLS are fetched from Gigablast API.
-> All seed URLS are stored in MySQL database among which some are filtered for crawling
-> Apache Nutch crawls over selected seed URLs and stores it's results in Solr index.

Searching (Takes less time)
---------
-> User enters some keywords, solr index is searched & results are displayed.
-> Also user entered keywords & results are stored in MySQL database.

