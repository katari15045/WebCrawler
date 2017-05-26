package com.github.katari15045;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NutchFeederServlet")
public class NutchFeederServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String values[] = request.getParameterValues("id");
		servletContext = request.getServletContext();
		LinkedHashSet<String> unrefinedTitleSet = (LinkedHashSet<String>) servletContext.getAttribute("unrefinedAPITitleSet");
		LinkedHashSet<String> unrefinedURLSet = (LinkedHashSet<String>) servletContext.getAttribute("unrefinedAPIURLSet");
		ArrayList<String> unrefinedTitleList = new ArrayList<String>();
		ArrayList<String> unrefinedURLList = new ArrayList<String>();
		unrefinedTitleList.addAll(unrefinedTitleSet);
		unrefinedURLList.addAll(unrefinedURLSet);
		int countValue = 0;
		
		while( countValue < values.length )
		{
			System.out.println( unrefinedTitleList.get( Integer.parseInt( values[countValue] ) ) );
			System.out.println( unrefinedURLList.get( Integer.parseInt( values[countValue] ) ) );
			System.out.println("------------------");
			
			countValue = countValue + 1;
		}
	}

}
