package com.github.katari15045;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/APIResultHandlerServlet")
public class APIResultHandlerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private APIResultHandlerService apiResultHandlerService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		apiResultHandlerService = new APIResultHandlerService();
		
		try 
		{
			apiResultHandlerService.start(request);
		}
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		response.sendRedirect("nutchCrawl.jsp");
	}

}
