<%@page import="com.github.katari15045.APIResult"%>
<%@page import="java.util.LinkedHashSet,java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>API Results</title>
</head>
<body>
	<%
		APIResult apiResult = (APIResult) application.getAttribute("apiResults");
		LinkedHashSet<String> titleSet = apiResult.getTitleSet();
		LinkedHashSet<String> urlSet = apiResult.getUrlSet();
		Iterator<String> titleIterator = titleSet.iterator();
		Iterator<String> urlIterator = urlSet.iterator();
		
		int count = 0;
	
		while( titleIterator.hasNext() )
		{
	%>
	<form action="APIResultHandlerServlet" method="post">
		<input type="checkbox" name="id" value="<%=count %>"> 
		<%=titleIterator.next() %> <br>
		<%=urlIterator.next() %> <br> <br>
	<%
			count = count + 1;
		}
	%>
	<br> <br>

		<input type="submit" value="Submit"> 
	</form>
</body>
</html>