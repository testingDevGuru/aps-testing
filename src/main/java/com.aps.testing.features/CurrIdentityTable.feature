Feature: Validate CURR_IDENTITY table data with different scenarios 

Scenario o Scenario outline: Combination scenarios
	Given   I connected to DB 
	When   I dropped a file in EC2 
	Then   DB should return "firstName", "lastName", "middleName", "gender", "dob", 
	"ssn", "npi", "businessName" and "ein" 
	Example: 
	||||||||