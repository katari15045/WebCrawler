<%@page import="com.github.katari15045.APIResult"%>
<%@page import="java.util.LinkedHashSet,java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API Search</title>
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
</style>
</head>
<body>

	<%
		
		String defaultQuery = (String) request.getAttribute("defaultQuery");
		String defaultMaxResultCount = (String) request.getAttribute("defaultMaxResultCount");
		boolean canIDisplayResults = (Boolean) request.getAttribute("canIDisplayResults");
	
	%>
	
	<form action="APIQueryServlet" method="post">
		Query : <input type="text" name="query" value="<%=defaultQuery%>"> <br> <br>
		Max Result Count : <input type="text" name="resultCount" value="<%=defaultMaxResultCount%>"> <br> <br> <br>
		<input type="submit" value="Search">
	</form>
	
	<br> <br> <br>
	
	<%
	
		if(canIDisplayResults)
		{
			APIResult apiResult = (APIResult) application.getAttribute("apiResults");
			LinkedHashSet<String> titleSet = apiResult.getTitleSet();
			LinkedHashSet<String> urlSet = apiResult.getUrlSet();
			Iterator<String> titleIterator = titleSet.iterator();
			Iterator<String> urlIterator = urlSet.iterator();
			
			int count = 0;
			String currentUrl;
		
			while( titleIterator.hasNext() )
			{
				currentUrl = urlIterator.next();
	%>		
				<form action="APIResultHandlerServlet" method="post">
				<input type="checkbox" name="id" value="<%=count %>">
				
			<span id="title"> <a href="<%=currentUrl%>"> <%=titleIterator.next() %> </a> </span> <br>
			<span id="url">	<%=currentUrl%> </span>  <br> <br>
				
	<%
				count = count + 1;
	
			}
	%>
			<br> <br>
		
				<input type="submit" value="Start Nutch Crawl"> 
			</form>
		
	<%
		}
	%>
</body>
</html>