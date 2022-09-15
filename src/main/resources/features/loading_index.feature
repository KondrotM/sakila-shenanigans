Feature: Loading the main page
  Scenario: Making sure the react page loads, rather than being shown 'You need javascript to run this page'
    Given I am using a modern browser
    When I load the index page
    Then The project logo is displayed