package com.github.katari15045;

import java.io.IOException;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		nutchCrawlService = new NutchCrawlService();
		nutchCrawlService.start();
	}

}
