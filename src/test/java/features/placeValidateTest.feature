# new feature
# Tags: optional

Feature: Validate REST APIs

  @AddPlace
  Scenario Outline: validate add place api for <name>
    Given Add Place Payload with <name> , <language> , <address>
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeID created above maps to "<name>" in "getPlaceAPI"

    Examples:
    | name | language | address   |
    | sp1  | hindi    | hyderabad |
  #   | sp2  | english  | goa       |
  #   | sp3  | odia     | odisha    |

@DeletePlace
  Scenario: validate delete place api
    Given Delete Place Payload
    When user calls "deletePlaceAPI" with "DELETE" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"

