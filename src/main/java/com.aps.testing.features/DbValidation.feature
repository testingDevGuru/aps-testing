Feature: Validate DB connectiona and a sample query 

Scenario: Verifing Db connection 
	Given I have DB connection 
	When I run sql query 
	Then I should get response data