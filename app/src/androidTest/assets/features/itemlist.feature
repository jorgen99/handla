Feature: Shopping list

  Scenario: It's possible to add one item
    Given I'm looking at the shoppinglist
    When I enter the text "Tomatoes" and hit return
    Then I should see "1" items in the list
    And item number "1" should read "Tomatoes"

  Scenario: Items are added to the top of the list
    Given I'm looking at the shoppinglist
    When I enter the text "Tomatoes" and hit return
    And I enter the text "Bananas" and hit return
    And I enter the text "Loaf of bread" and hit return
    Then I should see "3" items in the list
    And item number "1" should read "Loaf of bread"
    And item number "2" should read "Bananas"
    And item number "3" should read "Tomatoes"
