package com.github.katari15045;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NutchCrawlServlet")
public class NutchCrawlServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private NutchCrawlService nutchCrawlService;
	private RequestDispatcher requestDispatcher;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		nutchCrawlService = new NutchCrawlService();
		
		try
		{
			nutchCrawlService.start(request, response);
		}
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		request.setAttribute("defaultQuery", "");
		request.setAttribute("defaultMaxResultCount", "");
		request.setAttribute("canIDisplayResults", false);
		requestDispatcher = request.getRequestDispatcher("solrSearch.jsp");
		requestDispatcher.forward(request, response);
	}

}
