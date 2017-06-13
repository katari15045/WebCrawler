package com.github.katari15045;

import java.io.IOException;
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
		solrResultSet = solrSearchService.start(request);
		
		request.setAttribute("solrResultSet", solrResultSet);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("solrResults.jsp");
		requestDispatcher.forward(request, response);
	}

}
