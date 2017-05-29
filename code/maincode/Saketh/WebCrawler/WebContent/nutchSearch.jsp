<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nutch Search</title>
</head>
<body>
	<form action="NutchSearchServlet" method="post">
		Query : <input type="text" name="query"> <br> <br>
		Max Result Count : <input type="text" name="maxResultCount"> <br> <br>
		<input type="submit" value="search">
	</form>
</body>
</html>