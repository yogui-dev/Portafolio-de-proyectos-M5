Feature: Login to the-internet.herokuapp.com
  As a user, I want to login with valid credentials and see error with invalid ones

  @smoke @login
  Scenario Outline: Login attempts with different credentials
    Given I am on the login page
    When I login with username "<username>" and password "<password>"
    Then login should be "<result>"
    And message should contain "<message>"

    Examples:
      | username  | password               | result  | message                               |
      | tomsmith  | SuperSecretPassword!   | success | You logged into a secure area!        |
      | tomsmith  | wrongPass              | error   | Your password is invalid!             |
      | wrongUser | SuperSecretPassword!   | error   | Your username is invalid!             |
