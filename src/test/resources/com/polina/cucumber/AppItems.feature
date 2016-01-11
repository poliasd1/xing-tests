Feature: Check if an item can be successfully created

	Scenario: Adding new Item
		Given I click on new item
		When I enter name "Test 1"
		And I enter description "Description 1"
		And I press create item
		Then Item is created
		
	Scenario: New Item exists in List
		Given I click on new item
		When I enter name "Test 2"
		And I enter description "Description 2"
		And I press create item
		And I go Back
		Then Item exists in main view
	
	Scenario: Edit newly created Item
		Given I click on new item
		When I enter name "Test 3"
		And I enter description "Description 3"
		And I press create item
		And I click on edit item
		And I enter name "Test Edited"
		And I enter description "Description Edited"
		And I press update item
		Then Item is updated

	Scenario: Destroy last Item
		Given I click on show last item
		Then Item is shown
		

	Scenario: Show last Item
		Given I click on destroy last item		
		Then Last items are deleted