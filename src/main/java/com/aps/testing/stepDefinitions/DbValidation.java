package com.aps.testing.stepDefinitions;

import java.sql.ResultSet;

import com.aps.testing.util.DbConnection;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DbValidation {
	DbConnection dbCon = new DbConnection();
	ResultSet rs=null;

	@Given("^I have DB connection$")
	public void i_have_DB_connection() throws Throwable {
		dbCon.setDbConnection();

	}

	@When("^I run sql query$")
	public void i_run_sql_query() throws Throwable {
		String query="";
		rs=dbCon.executeQuery(query);
		throw new PendingException();
	}

	@Then("^I should get response data$")
	public void i_should_get_response_data() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
