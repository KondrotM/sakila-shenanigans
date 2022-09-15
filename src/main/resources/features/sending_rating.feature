Feature: Sending a rating
  Scenario Outline: The user sends a rating on a
    Given I am using a modern browser
    When I load the browse movie page
    And I send an "<emotion>" rating
    Then The "<emotion>" ratings are increased by one

    Examples:
    | emotion |
    | wow     |
    | xd      |
    | love    |
    | shock   |

