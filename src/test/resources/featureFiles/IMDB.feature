Feature: IMDB

  As a film person
  I want my IMDB facts handy
  So I can enjoy my films and not have to worry about how to get my facts

  Scenario: Search for films by director

    Given the director "Darren Aronofsky"
    When I search IMDB
    Then I should find "14" films directed
    And those films should include
    |Black Swan         |2010|
    |The Wrestler       |2008|
    |The Fountain       |2006|
    |Requiem for a Dream|2000|
    |Pi                 |1998|
