Feature: Add Single Product

  Scenario Outline: Add a single product to cart

    Given user opens the login page
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks the login button
    Then user should verify login result "<result>"

    When user selects a product
    And user navigates to cart page
    Then user verifies the selected product in cart

    Examples:
      | username      | password     | result  |
      | standard_user | secret_sauce | success |