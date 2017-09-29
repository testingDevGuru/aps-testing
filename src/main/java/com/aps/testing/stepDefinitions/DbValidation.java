package com.aps.testing.stepDefinitions;

import java.sql.ResultSet;

import com.aps.testing.util.DbConnection;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DbValidation {
	DbConnection dbCon = new DbConnection();
	ResultSet rs = null;

	@Given("^I have DB connection$")
	public void i_have_DB_connection() throws Throwable {
		dbCon.setDbConnection();

	}

	@When("^I run sql query$")
	public void i_run_sql_query() throws Throwable {
		String query = "Select LAST_NAME, FIRST_NAME, MIDDLE_NAME, GENDER, DOB, SSN, NPI, BUSINESS_NAME, EIN \n"
				+ "from STAGE1.CURR_IDENTITY where rownum<10";
		rs = dbCon.executeQuery(query);

	}

	@Then("^I should get response data$")
	public void i_should_get_response_data() throws Throwable {
		while (rs.next()) {
			String firstName = rs.getString("FIRST_NAME");
			String lastName = rs.getString("LAST_NAME");
			String gender = rs.getString("GENDER");

			System.out.println("First name is: " + firstName);
			System.out.println("Last name is: " + lastName);
			System.out.println("Gender is: " + gender);

		}
	}
	
	@Then("^I close DB connection$")
	public void i_close_DB_connection() throws Throwable {
	   dbCon.closeConnection();
	}

}
