Feature: Purchase Hummingbird Printed Sweater

  Scenario Outline: Purchase Hummingbird Printed Sweater with specific parameters
    Given the user is logged in with email '<email>' and password '<password>'
    When the user selects the Hummingbird Printed Sweater
    Then the discount should be 20%
    When the user selects size '<size>'
    And the user selects '<quantity>' pieces
    And the user adds the product to the cart
    And the user goes to checkout
    And the user confirms the address
    And the user selects the pickup method 'PrestaShop pick up in store'
    And the user selects the payment option 'Pay by Check'
    And the user places the order
    Then the user takes a screenshot of the order confirmation and the amount
    And the user goes to order history
    Then the order should be listed with the status 'Awaiting check payment' and the same amount

    Examples:

    | email |       password | size | quantity |
    |bla@gmail.com| blabla123| M    |  5       |


