#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Customers

	
Background: Below are the common steps for each scenario
	  Given User Launch Chrome Browser
    When User opens URL "http://admin-demo.nopcommerce.com"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboard

@sanity
Scenario: Add a new Customer
    When User click on customers Menu
    And Click on customers Menu Item
    And Click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And Click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And Close browser
  
@regression
Scenario: Search Customer by Email
    When User click on customers Menu
    And Click on customers Menu Item
		And Enter customer Email
		When Click on search button
		Then User Email should be found in the Search table
		And  Close browser 

@regression
Scenario: Search Customer by Name
    When User click on customers Menu
    And Click on customers Menu Item
		And Enter customer FirstName
		And Enter customer LastName
		When Click on search button
		Then User Name should be found in the Search table
		And Close browser 
