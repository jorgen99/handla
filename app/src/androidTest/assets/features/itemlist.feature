Feature: Shopping list

  Background:
    Given I'm looking at the shoppinglist
    When I enter the text "Tomater" and hit return

  Scenario: It's possible to add one item
    Then I should see 2 items in the list
    And item number 1 should be a category header named "Frukt & Grönt"
    And item number 2 should be an item of type "Tomater"


  Scenario: Items are sorted within each category
    And I enter the text "Bananer" and hit return
    And I enter the text "Bacon" and hit return
    Then I should see 5 items in the list
    And item number 1 should be a category header named "Frukt & Grönt"
    And item number 2 should be an item of type "Bananer"
    And item number 3 should be an item of type "Tomater"
    And item number 4 should be a category header named "Kött & Chark"
    And item number 5 should be an item of type "Bacon"
