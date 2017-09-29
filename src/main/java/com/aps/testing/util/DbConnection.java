package com.aps.testing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	public static Connection con = null;
	public static ResultSet rs = null;
	public static Statement stmt=null;

	public void setDbConnection() throws SQLException {
		try {
			Class.forName(TestConfig.dbDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find JDBC driver.");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!");

		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@apsdb-reengineering.cjqj4gfuhy2n.us-east-1.rds.amazonaws.com:1521:APSDB",
					TestConfig.DbUserName, TestConfig.dbPassword);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (con != null) {
			System.out.println("Successfully connected to DB.");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	public ResultSet executeQuery(String query) {

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			System.err.println("Error occured: " + e.getMessage());
		}

		return rs;

	}
	
	public void closeConnection() throws SQLException {
		try {
			rs.close();
			stmt.close();
			con.close();
			System.out.println("Connection closed successfully.");
		} catch (SQLException e) {
			System.out.println("Error occured while closing connection.");
			e.printStackTrace();
		}
		
	}
}
