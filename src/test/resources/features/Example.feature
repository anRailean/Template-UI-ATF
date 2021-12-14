@Example
Feature: Example

  Scenario: Base amazon search
    Given user navigates to 'Amazon Landing Page'
    When  user searches for 'Cucumber' in search bar
    Then Search Results Page contains at least 1 result
    And Search Results Page contains results for 'Cucumber'