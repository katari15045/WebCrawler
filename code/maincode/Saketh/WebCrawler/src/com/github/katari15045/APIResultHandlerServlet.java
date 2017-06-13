package com.github.katari15045;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
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
	private ServletContext servletContext;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		apiResultHandlerService = new APIResultHandlerService();
		
		try 
		{
			String[] selectedUrls = apiResultHandlerService.start(request);
			servletContext = request.getServletContext();
			servletContext.setAttribute("selectedUrls", selectedUrls);
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
