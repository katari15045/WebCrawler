<%@page import="java.util.Iterator"%>
<%@page import="com.github.katari15045.SolrResult"%>
<%@page import="java.util.LinkedHashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solr Results</title>
<style type="text/css">
	#title
	{
		color: #0000EE;
	}
	
	#title a
	{
		text-decoration: none;
	}
	
	#title a:HOVER
	{
		text-decoration: underline;
	}
	
	#url
	{
		color: #3a6d33;
		font-size: 12px;
	}
	
	#content
	{
		font-size: 12px;
	}
</style>
</head>
<body>
	
	<%
		LinkedHashSet<SolrResult> solrResultSet = (LinkedHashSet<SolrResult>) request.getAttribute("solrResultSet");
		Iterator iterator = solrResultSet.iterator();
		SolrResult currentSolrResult;
		
		while( iterator.hasNext() )
		{
			currentSolrResult = (SolrResult) iterator.next();
	%>
	<span id="title"> <a href="<%= currentSolrResult.getUrl() %>"> <%= currentSolrResult.getTitle() %> </a> </span> <br>
	<span id="url">	<%= currentSolrResult.getUrl() %> </span> <br>
	<span id="content">	<%= currentSolrResult.getContent() %> </span> <br> <br>
	
	<%
		}
	%>
	
</body>
</html>