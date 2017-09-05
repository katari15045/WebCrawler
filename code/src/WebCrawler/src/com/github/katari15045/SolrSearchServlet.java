package com.github.katari15045;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SolrSearchServlet")
public class SolrSearchServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private SolrSearchService solrSearchService;
	private LinkedHashSet<SolrResult> solrResultSet;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		solrSearchService = new SolrSearchService();
		
		try
		{
			solrResultSet = solrSearchService.start(request);
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		request.setAttribute("solrResultSet", solrResultSet);
		request.setAttribute("canIDisplayResults", true);
		request.setAttribute("defaultQuery", request.getParameter("query"));
		request.setAttribute("defaultMaxResultCount", request.getParameter("maxResultCount"));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("solrSearch.jsp");
		requestDispatcher.forward(request, response);
	}

}
