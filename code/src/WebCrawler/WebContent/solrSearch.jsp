<%@page import="java.util.Iterator"%>
<%@page import="com.github.katari15045.SolrResult"%>
<%@page import="java.util.LinkedHashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solr Search</title>
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
		
		String defaultQuery = (String) request.getAttribute("defaultQuery");
		String defaultMaxResultCount = (String) request.getAttribute("defaultMaxResultCount");
		boolean canIDisplayResults = (Boolean) request.getAttribute("canIDisplayResults");
	
	%>
	
	<form action="SolrSearchServlet" method="post">
		Query : <input type="text" name="query" value="<%=defaultQuery%>"> <br> <br>
		Max Result Count : <input type="text" name="maxResultCount" value="<%=defaultMaxResultCount%>"> <br> <br>
		<input type="submit" value="Search">
	</form>
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<form action="home.jsp">
		<input type="submit" value="Back to Home" />
	</form>
	
	<%
	
		if(canIDisplayResults)
		{
	
	%>
		<br> <br> <br>
		
		<%
			LinkedHashSet<SolrResult> solrResultSet = (LinkedHashSet<SolrResult>) request.getAttribute("solrResultSet");
			System.out.println("Before iterator init");
			Iterator iterator = solrResultSet.iterator();
			System.out.println("After Iterator init");
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
		
	<%
	
		}
	
	%>
	
</body>
</html>