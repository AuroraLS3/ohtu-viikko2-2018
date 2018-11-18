Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new user is selected
    When  username "yes" and password "haha m33m#" are entered
    Then  system will respond with "new user registered"

  Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When  username "yes" and password "haha m33m#" are entered
    When  username "yes" and password "haha m44m#" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When  username "no" and password "haha m33m#" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and too short password
    Given command new user is selected
    When  username "yes" and password "haha" are entered
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and password enough long but consisting of only letters
    Given command new user is selected
    When  username "yes" and password "hahayess" are entered
    Then  system will respond with "new user not registered"

  Scenario: can login with successfully generated account
    Given user "eero" with password "salainen1" is created
    And   command login is selected
    When  username "eero" and password "salainen1" are entered
    Then  system will respond with "logged in"
