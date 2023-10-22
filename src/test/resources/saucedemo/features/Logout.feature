Feature: Logout User Saucedemo

  @Logout
  Scenario Outline: Logout
    Given Login page of saucedemo
    When User input <username> in username field
    And User input <password> in password field
    And User click login button
    Then user verify <status> login result
    Then User click burger button
    Then User click Logout button
    Then User verify login page of saucedemo

    Examples:
      | username         | password      | status |
      | standard_user | secret_sauce  | success |
