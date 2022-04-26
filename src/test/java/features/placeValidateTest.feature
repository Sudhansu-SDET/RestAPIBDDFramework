# new feature
# Tags: optional

Feature: Validate something

    Scenario: validate rest response
      Given Add Place Payload
      When user calls AddPlace API with POST http request
      Then the API call is success with status code 200
      And "status" in response body is "OK"
      And "scope" in response body is "APP"