Feature: Validate what's in DB against the content of  XML 
  Scenario: Taking input data from XML file and run a validation on how to gets written to the DB
    Given I upload xml file using a shell script using a <user> and a <host> and <privateKey> and <file>
    |ec2-user|<user>|
    |54.174.19.252|<host>|
    |myEC2Puty.ppk|<privateKey>|
    |test.bat|<file>|
    When I query the curr database  for the provider <provider>
    #Then the <firstName>,  <lastName>, <SSN> and <DOB> from the XML should match with the output of the DB