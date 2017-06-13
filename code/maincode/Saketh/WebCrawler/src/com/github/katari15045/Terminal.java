
package com.github.katari15045;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Terminal
{
	private String command;
	private StringBuilder output;

	public void start(String inpFile)
	{
		StringBuilder path = new StringBuilder();
		path.append( System.getProperty("user.dir") ).append("/").append(inpFile);
		
		command = "bash " + path.toString();
		System.out.println("Executing " + path.toString() + "..." );
		executeCommand();
		
		System.out.println("------------------------------\nTerminal starts\n------------------------------\n");
		//System.out.print( output.toString() );
		System.out.println("------------------------------\nTerminal ends\n------------------------------\n");
	}
	private void executeCommand()
	{
		Process process;
		BufferedReader bufferedReader = null;
		String currentLine;

		output = new StringBuilder();

		try
		{
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			currentLine = bufferedReader.readLine();

			while( currentLine != null )
			{
				output.append(currentLine);
				output.append("\n");
				System.out.println(currentLine);
				currentLine = bufferedReader.readLine();
			}

			bufferedReader.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}