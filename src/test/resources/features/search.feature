Feature: Search
  In order to find pages on the web
  As an information seeker
  I want to be able to search using keywords

  Scenario Outline: Search for keywords that yield results in Firefox
    Given I am using "Firefox" and open Google
    When I query on "<queryText>"
    Then I should see "<queryResults>"

    Examples:
      |queryText     |queryResults                                |
      |cucumber bdd  |Cucumber is for Behaviour-Driven Development|
      |Bernie Sanders|Bernie Sanders for President                |
      |Howard Deiner |Deinersoft, Inc.                            |

  Scenario Outline: Search for keywords that yield results in Edge
    Given I am using "Chrome" and open Google
    When I query on "<queryText>"
    Then I should see "<queryResults>"

    Examples:
      |queryText     |queryResults                                |
      |cucumber bdd  |Cucumber is for Behaviour-Driven Development|
      |Bernie Sanders|Bernie Sanders for President                |
      |Howard Deiner |Deinersoft, Inc.                            |

  Scenario Outline: Search for keywords that yield results in IE
    Given I am using "Internet Explorer" and open Google
    When I query on "<queryText>"
    Then I should see "<queryResults>"

    Examples:
      |queryText     |queryResults                                |
      |cucumber bdd  |Cucumber is for Behaviour-Driven Development|
      |Bernie Sanders|Bernie Sanders for President                |
      |Howard Deiner |Deinersoft, Inc.                            |
