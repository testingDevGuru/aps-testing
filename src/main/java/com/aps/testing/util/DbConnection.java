package com.aps.testing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	public static Connection con = null;

	public void setDbConnection() throws SQLException {
		try {
			Class.forName(TestConfig.dbDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find JDBC driver.");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(TestConfig.DbUrl, TestConfig.DbUserName, TestConfig.dbPassword);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Successfully connected to DB.");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			System.err.println("Error occured: " + e.getMessage());
		}

		return rs;

	}
}
