package com.github.katari15045;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		apiQueryService = new APIQueryService();
		query = request.getParameter("query");
		resultCount = Integer.parseInt( request.getParameter("resultCount") );
		apiResult = apiQueryService.start(query, resultCount);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("apiResults.jsp");
		request.setAttribute("titleSet", apiResult.getTitleSet() );
		request.setAttribute("urlSet", apiResult.getUrlSet() );
		requestDispatcher.forward(request, response);
	}

}
