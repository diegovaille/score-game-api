# new feature
# Tags: optional

Feature: Game Score Requests

  Background:
    Given there are these scores already in the database:
    | userId | score | position |
    | 1      | 1000  | 1        |
    | 2      | 900   | 2        |
    | 4      | 800   | 3        |

  Scenario: Client sends a POST with userId and points to /score
    When client sends score with userId 5 and points 500
    Then the client receives status code of 200

  Scenario: Client sends a POST with empty userId to /score
    When client sends score with userId NULL and points 500
    Then the client receives status code of 400

  Scenario: Client sends a GET to /highscorelist
    When client sends a GET to highscorelist
    Then the client receives status code of 200
    And the response body has a highscorelist

  Scenario: Client sends a GET for a valid /{userId}/position
    When client sends a GET with userId 1
    Then the client receives status code of 200
    And the response has a body object with userId 1, points 1000 and position 1

  Scenario: Client sends a GET for a valid /{userId}/position
    When client sends a GET with userId 2
    Then the client receives status code of 200
    And the response has a body object with userId 2, points 900 and position 2

  Scenario: Client sends a GET for a valid /{userId}/position
    When client sends a GET with userId 3
    Then the client receives status code of 200
    And the response has an emtpy body object