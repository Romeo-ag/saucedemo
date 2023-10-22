Feature: Order

  @Cart
  Scenario Outline: Add item
    Given Login page of saucedemo
    When User input standard_user in username field
    And User input secret_sauce in password field
    And User click login button
    Then user verify <status> login result
    Then User add to card
      | sauce-labs-backpack |
      | sauce-labs-bike-light |
      | sauce-labs-bolt-t-shirt |
      | sauce-labs-fleece-jacket |
      | sauce-labs-onesie |
      | test.allthethings()-t-shirt-(red) |


  @Cart
  Scenario Outline: Add and remove item
    Given Login page of saucedemo
    When User input standard_user in username field
    And User input secret_sauce in password field
    And User click login button
    Then user verify <status> login result
    Then User add item to cart
      | sauce-labs-backpack |
      | sauce-labs-bike-light |
      | sauce-labs-bolt-t-shirt |
      | sauce-labs-fleece-jacket |
      | sauce-labs-onesie |
      | test.allthethings()-t-shirt-(red) |
    Then User remove item from cart
      | sauce-labs-backpack |
      | sauce-labs-bike-light |
      | sauce-labs-bolt-t-shirt |
      | sauce-labs-fleece-jacket |
      | sauce-labs-onesie |
      | test.allthethings()-t-shirt-(red) |



#  @Order @Negative
#  Scenario Outline: Simulate Add and remove item using problem user
#    Given Login page of saucedemo
#    When User input standard_user in username field
#    And User input secret_sauce in password field
#    And User click login button
#    Then user verify <status> login result
#    Then User add <item> to cart
#    Then User remove <item> from cart
#
#    Examples:
#      | item                       |
#      | sauce-labs-backpack |
#      | sauce-labs-bike-light |
#      | sauce-labs-bolt-t-shirt |
#      | sauce-labs-fleece-jacket |
#      | sauce-labs-onesie |
#      | test.allthethings()-t-shirt-(red) |