@Regression @EpicLogin
Feature: 3: Login-Logout

  @Start
  Scenario Outline: 1: Successful Login
    Given the user is on the employee login page
    When the user enters <username> and <password> in the credential fields
    And the user clicks the login button
    Then the user <username> will be logged in successfully

    Examples: 
      | username               | password |
      | EmployeeAutomationTest | test     |

  Scenario: 2: Logout
    Given the user is logged in
    And the user is on the employee list page
    When the user logs out
    Then the user will be back on the employee login page

  @End
  Scenario Outline: 3: Invalid Login
    Given the user is on the employee login page
    When the user enters <username> and <password> in the credential fields
    And the user clicks the login button
    Then the feedback message "Invalid username or password." will be displayed

    Examples: 
      | username               | password |
      | EmployeeAutomationTest | invalid  |
