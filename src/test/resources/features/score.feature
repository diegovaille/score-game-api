# new feature
# Tags: optional

Feature: Game Score Requests

  Scenario: Client sends a POST with userId and points to /score
    When client sends score with userId 1 and points 500
    Then the client receives status code of 200
    And the score was saved in the database

  Scenario: Clients sends a POST with empty userId to /score
    When client sends score with userId NULL and points 500
    Then the client receives status code of 400

  Scenario: Clients sends a GET for a valid /{userId}/position
    Given there is a userId 1 with 500 points and position 3 in the database
    When client sends a GET with userId 1
    Then the client receives status code of 200
    And the response has a body object with userId 1, points 500 and position 3