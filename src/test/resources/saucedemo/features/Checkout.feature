Feature: Checkout

  @Checkout
  Scenario Outline: Checkout item in cart
    Given Login page of saucedemo
    When User input standard_user in username field
    And User input secret_sauce in password field
    And User click login button
    Then user verify <status> login result
    Then User add item to cart
      | sauce-labs-backpack |
      | sauce-labs-bike-light |
    Then user click cart icon
    Then User click checkout
    And user input First Name
    And User input Last name
    And User input Postal Code
    Then User click Continue
    Then User click finish
    Then User verify Checkout Complete Page




#    Examples:
#      | item                       |
#      | sauce-labs-backpack |
#      | sauce-labs-bike-light |
