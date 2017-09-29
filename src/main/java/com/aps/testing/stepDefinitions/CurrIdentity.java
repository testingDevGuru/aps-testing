package com.aps.testing.stepDefinitions;

import java.util.List;

import org.junit.Assert;

import com.aps.testing.helper.CurrIdentityHelper;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CurrIdentity {
	
	@Given("^I upload xml file using a shell script using a <user> and a <host> and <privateKey> and <file>$")
	public void i_upload_xml_file_using_a_shell_script_using_a_user_and_a_host_and_privateKey_and_file(DataTable arg1) throws Throwable {
		List <List<String>> data = arg1.raw();
    	CurrIdentityHelper currIdentityHelper = new CurrIdentityHelper();
    	String outcome = currIdentityHelper.runExec(data.get(0).get(0),data.get(1).get(0),data.get(2).get(0),data.get(3).get(0));
    	Assert.assertEquals("exit-status: 0", outcome);
	}
	
	
	@When("^I query the curr database  for the provider <provider>$")
	public void i_query_the_curr_database_for_the_provider_provider() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("database queries will happen here");
	}

	@Then("^the <firstName>,  <lastName>, <SSN> and <DOB> from the XML should match with the output of the DB$")
	public void the_firstName_lastName_SSN_and_DOB_from_the_XML_should_match_with_the_output_of_the_DB() throws Throwable {
		System.out.println("Asserting the Database data against the XML data will happen here");
	}

}
