Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new user is selected
    When  a valid username "liisa" is entered
    And password "salainen1" is entered and matching password confirmation are entered
    And submit is clicked
    Then  a new user is created

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When  invalid username "b" is entered
    And password "salainen1" is entered and matching password confirmation are entered
    And submit is clicked
    Then user is not created and error "username should have at least 3 characters" is reported

  Scenario: creation fails with correct username and too short password
    Given command new user is selected
    When  a valid username "liisa" is entered
    And invalid password "a" is entered and matching password confirmation are entered
    And submit is clicked
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails when password and password confirmation do not match
    Given command new user is selected
    When  a valid username "liisa" is entered
    And password "salainen1" is entered and invalid password confirmation are entered
    And submit is clicked
    Then user is not created and error "password and password confirmation do not match" is reported