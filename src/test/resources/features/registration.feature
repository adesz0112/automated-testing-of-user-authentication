Feature: User Registration

  Scenario: Successful registration
    Given I am on the registration page
    And I register a valid user
    When I click the Sign Up button
    Then I should be registered to the page

  Scenario Outline: Successful registration with valid credentials
    Given I am on the registration page
    And I enter name "<name>" and email "<email>"
    And I enter password "<password>" and choose gender "<gender>"
    And I select date of birth: day "<day>", month "<month>", year "<year>"
    And I enter firstname "<firstName>", lastname "<lastName>", company "<company>"
    And I enter address1 "<address1>", and address2 "<address2>"
    And the user selects country "<country>", and enters state "<state>", city "<city>"
    And the user enters zipcode "<zipcode>" and phone number "<phone>"
    When I click the Sign Up button
    Then I should be registered to the page

    Examples:
      | name   | email                   | password  | gender | day | month | year | firstName | lastName | company    | address1 | address2 | country     | state   | city      | zipcode | phone      |
      | Anna   | anna.kiss1@example.com  | Anna123!  | female | 12  | 6     | 1990 | Anna      | Kiss     | InnoTech   | Fő utca  | 2. em    | India       | Pest    | Budapest  | 1111    | 0620111222 |
      | Bence  | bence.nagy1@example.com | Bence321! | male   | 5   | 3     | 1985 | Bence     | Nagy     | CodeSprint | Kossuth  | 1. ajtó  | Australia   | Baranya | Pécs      | 7624    | 0630123456 |
      | Eva    | eva.toth1@example.com   | Eva456!   | female | 20  | 11    | 1993 | Eva       | Toth     | SmartLogic | Petőfi   | fszt. 4  | Canada      | Bács    | Kecskemét | 6000    | 0670123456 |
