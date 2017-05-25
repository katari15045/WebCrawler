package com.github.katari15045;

import java.io.IOException;


public class Terminal
{
	public void start(String inpFile) throws IOException
	{
		StringBuilder path = new StringBuilder();
		path.append( System.getProperty("user.dir") ).append("/").append(inpFile);
		ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", path.toString() );
		processBuilder.start();
	}
}