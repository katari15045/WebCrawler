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
		LinkedHashSet<String> titleSet = (LinkedHashSet) application.getAttribute("unrefinedAPITitleSet");
		LinkedHashSet<String> urlSet = (LinkedHashSet) application.getAttribute("unrefinedAPIURLSet");
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