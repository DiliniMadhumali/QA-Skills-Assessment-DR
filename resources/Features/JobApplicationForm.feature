@tag
Feature: Student Registration Form
  
@tag1
  Scenario: Fill out the form and submit successfully
 	  Given I am on the Student Registration Form page
    When user enter the firstname "<firstname>"
    And user enter the lastname "<lastname>"
    And user enter the email "<email>"
    And user select gender "<gender>"
    And user enter the mobilenumber "<mobilenumber>"
    And user enter the dateofbirth "<dateofbirth>"
    And user enter the subject "<subject>"
    And user select the hobby "<hobby>"
    And user upload the picture "<picture>"
    And user enter the address "<address>"
    And user select the state "<state>" and City "<city>"
    And user click on submit button
    Then user should see a confirmation message
       
@tag2
# user does not enter lastname and mobilenumber
  Scenario: Form submission with mandatory fields left blank
 	  Given I am on the Student Registration Form page
    When user enter the firstname "<firstname>"
    And user enter the email "<email>"
    And user select gender "<gender>"
    And user click on submit button
    Then user should not see a confirmation message
    And user should remain on the Student Registration Form page


 @tag3
 # user enter invalid email
  Scenario: Form submission with invalid email
 	  Given I am on the Student Registration Form page
    When user enter the firstname "<firstname>"
    And user enter the lastname "<lastname>"
    And user enter the invalid email "<email>"
    And user select gender "<gender>"
    And user enter the mobilenumber "<mobilenumber>"
    And user enter the dateofbirth "<dateofbirth>"
    And user enter the subject "<subject>"
    And user select the hobby "<hobby>"
    And user upload the picture "<picture>"
    And user enter the address "<address>"
    And user select the state "<state>" and City "<city>"
    And user click on submit button
    Then user should still on the Student Registration Form page
    

