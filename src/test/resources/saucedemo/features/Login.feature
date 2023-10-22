Feature: Login User Saucedemo

  @Login @success
  Scenario Outline: Login using success user
    Given Login page of saucedemo
    When User input <username> in username field
    And User input <password> in password field
    And User click login button
    Then user verify <status> login result

    Examples:
    | username         | password      | status |
    | standard_user | secret_sauce  | success |
#    | standard_user | wrongpass     | invalid_pass |
#    | locked_out_user | secret_sauce     | locked |
#    | problem_user | secret_sauce     | success |
#    | performance_glitch_user | secret_sauce     | failed |
#    | error_user | secret_sauce     | success |

  @Login @negative
  Scenario Outline: Login using invalid pass and locked user
    Given Login page of saucedemo
    When User input <username> in username field
    And User input <password> in password field
    And User click login button
    Then user verify <status> login result

    Examples:
      | username         | password      | status |
     | standard_user | wrongpass     | invalid_pass |
        | locked_out_user | secret_sauce     | locked |
