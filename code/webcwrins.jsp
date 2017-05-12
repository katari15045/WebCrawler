<%@ page contentType="text/html;" language="java" import="java.sql.*,java.io.*,java.text.*,java.util.zip.*,java.util.*,java.security.*,sun.misc.BASE64Encoder,java.net.*,org.json.simple.*,org.json.simple.parser.JSONParser,java.text.SimpleDateFormat,java.util.Date" errorPage="" %>
<%@include file="dbconn.jsp"%>
<head>
</head>
<body>
       
<%	
try
{
	String usrnm=request.getParameter("userid");
	String passkey=request.getParameter("key");
	String searchcont=request.getParameter("scontent");
	String searchpage=request.getParameter("spage");

	if(request.getParameter("scontent")!=null && request.getParameter("spage")!=null)
	{
		String rawdata=SearchFeed(searchcont,searchpage);
		JSONParser parser = new JSONParser();
		//Object objcr = parser.parse(rawdata);
		//out.println(objcr);	
		out.println(rawdata);	
	}
	else
	{
		out.println("Invalid Input");
	}
}	

catch(Exception e)
{
	out.println(e);
}

	%>
</body>
</html>


<%!
public static final synchronized String SearchFeed(String srcontent,String spageno)
{
    String resp="";
      try
	{
	  srcontent=java.net.URLEncoder.encode(srcontent, "UTF-8");
	  spageno=java.net.URLEncoder.encode(spageno, "UTF-8");

	  URL url5 = new URL ("https://www.gigablast.com/search?q="+srcontent+"&format=json&userid=138&code=1461895544&s="+spageno);
	  URLConnection url5Con= url5.openConnection();
	  DataInputStream  in = new DataInputStream(url5Con.getInputStream());
	  String strSVContent;
	  while (null != ((strSVContent = in.readLine())))
	  {
	  	resp+=strSVContent;
	  }
	  in.close();
	  }
	  catch(Exception e)
	  {
	    	resp="Java Error : "+e.toString();
	  }

    return resp;
}
%>
