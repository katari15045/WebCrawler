<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>API Query</title>
</head>
<body>
	<form action="APIQueryServlet" method="post">
		Query : <input type="text" name="query"> <br> <br>
		Result Count : <input type="text" name="resultCount"> <br> <br> <br>
		<input type="submit" value="Search">
	</form>
</body>
</html>