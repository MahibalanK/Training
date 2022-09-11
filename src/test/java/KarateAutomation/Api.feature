Feature: api testing features using karate
  Scenario: testing the get call for User Details
    Given url 'https://reqres.in/api/users?page=2'
    When method GET
    Then status 200
