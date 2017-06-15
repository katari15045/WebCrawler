package com.github.katari15045;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/APISearchForwarderServlet")
public class APISearchForwarderServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("defaultQuery", "");
		request.setAttribute("defaultMaxResultCount", "");
		request.setAttribute("canIDisplayResults", false);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("apiSearch.jsp");
		requestDispatcher.forward(request, response);
	}

}
