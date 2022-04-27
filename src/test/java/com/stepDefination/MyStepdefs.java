package com.stepDefination;

import Resources.APIEnum;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {
    RequestSpecification request;
    ResponseSpecification responsespecbuilder;
    Response response;
    Response rawResponse;
    TestDataBuild data = new TestDataBuild();
    public static String place_id;

    @Given("^Add Place Payload with (.*) , (.*) , (.*)$")
    public void addPlacePayloadWithNameLanguageAddress(String name, String language , String address) throws IOException {
        request = given().spec(Utils.requestSpecification()).body(data.addPlacePayload(name,language,address)); // given

    }

    @When("user calls {string} with {string} http request")
    public void userCallsAPIWithHttpRequest(String apiName,String httpmethod) {

        APIEnum resouceAPI = APIEnum.valueOf(apiName);

        responsespecbuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(httpmethod.equalsIgnoreCase("POST")){
            rawResponse = request // when
                    .when().post(resouceAPI.getResource());
        }
        else if(httpmethod.equalsIgnoreCase("GET")){
            rawResponse = request // when
                    .when().get(resouceAPI.getResource());
        }
        else if(httpmethod.equalsIgnoreCase("DELETE")){
            rawResponse = request // when
                    .when().delete(resouceAPI.getResource());
        }

        response = rawResponse
                .then().spec(responsespecbuilder).extract().response();

    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int code) {
        System.out.println(response.getStatusCode());
        assertEquals(code, response.getStatusCode());

    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        assertEquals(value,getJsonPath(response,key));
    }


    @And("verify placeID created above maps to {string} in {string}")
    public void verifyPlaceIDCreatedAboveMapsToIn(String name, String APIName) throws IOException {
        place_id = getJsonPath(response,"place_id");
        request = given().spec(Utils.requestSpecification()).queryParam("place_id",getJsonPath(response,"place_id"));
        userCallsAPIWithHttpRequest(APIName,"GET");

        String nameFromGetResponse = getJsonPath(response,"name");
        System.out.println("final name from get " + nameFromGetResponse);
        Assert.assertEquals(name,nameFromGetResponse);

    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {
        request = given().spec(Utils.requestSpecification()).body(data.deletePlacePayload(place_id)); // given
    }
}


