<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<form action="APISearchForwarderServlet" method="post">
		<input type="submit" value="Start Indexing in Solr">
	</form>
	
	<br> <br>
	
	<form action="SolrSearchForwarderServlet" method="post">
		<input type="submit" value="Start Searching" />
	</form>
</body>
</html>