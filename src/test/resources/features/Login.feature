
Feature: Login functionality

  Background:
    Given user opens the login page

  
  Scenario Outline: Login with different credentials

    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks the login button
    Then login result should be "<result>"

    Examples:
      | username      | password     | result  |
      | standard_user | secret_sauce | success |
      | invalid_user  | wrong123     | failure |



