package com.github.katari15045;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/APIQueryServlet")
public class APIQueryServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String query;
	private int resultCount;
	
	private APIQueryService apiQueryService;
	private APIResult apiResult;
	private ServletContext servletContext;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		getUserData(request);
		
		apiQueryService = new APIQueryService();
		apiResult = apiQueryService.start(query, resultCount);
		
		servletContext = request.getServletContext();
		servletContext.setAttribute("apiResults", apiResult);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("apiSearch.jsp");
		request.setAttribute("defaultQuery", query);
		request.setAttribute("defaultMaxResultCount", String.valueOf(resultCount));
		request.setAttribute("canIDisplayResults", true);
		requestDispatcher.forward(request, response);
	}
	
	private void getUserData(HttpServletRequest request)
	{
		query = request.getParameter("query");
		resultCount = Integer.parseInt( request.getParameter("resultCount") );
	}

}
