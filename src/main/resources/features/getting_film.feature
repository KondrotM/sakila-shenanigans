Feature: Getting a film from the backend
  Scenario: Example of a request made by the front-end to the backend.
    Given I have a film ID
    And The ID has a corresponding movie in the metadata
    When I fetch a movie by ID
    Then A film with that ID is returned