package com.github.katari15045;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database 
{
	private Connection connection;
	private Statement statement;
	private String databaseURL;
	private String databaseUserID;
	private String databasePassword;
	
	public Database()
	{
		databaseURL = "jdbc:mysql://localhost:3306/test_db";
		databaseUserID = "test_user";
		databasePassword = "Test_user9977";
	}
	
	public void makeConnection() throws ClassNotFoundException, SQLException
	{	
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(databaseURL, databaseUserID, databasePassword);
		statement = connection.createStatement();
	}
	
	public ResultSet executeQuery(PreparedStatement preparedStatement)
	{
		ResultSet resultSet = null;
		
		try 
		{
			resultSet = preparedStatement.executeQuery();
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public int executeUpdate(PreparedStatement preparedStatement)
	{
		int result = 0;
		
		try
		{
			result = preparedStatement.executeUpdate();
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void closeConnection()
	{
		try 
		{
			connection.close();
			statement.close();
		}
		
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	// ---------------Getters & Setters---------------------

	public String getDatabaseURL()
	{
		return databaseURL;
	}

	public void setDatabaseURL(String databaseURL) 
	{
		this.databaseURL = databaseURL;
	}

	public String getDatabaseUserID() 
	{
		return databaseUserID;
	}

	public void setDatabaseUserID(String databaseUserID) 
	{
		this.databaseUserID = databaseUserID;
	}

	public String getDatabasePassword() 
	{
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) 
	{
		this.databasePassword = databasePassword;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
}
