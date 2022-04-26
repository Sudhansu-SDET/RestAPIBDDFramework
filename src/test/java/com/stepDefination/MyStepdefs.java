package com.stepDefination;

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
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {
    RequestSpecification request;
    ResponseSpecification responsespecbuilder;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload")
    public void addPlacePayload() throws FileNotFoundException {

        System.out.println("given");

        request = given().spec(Utils.requestSpecification()).body(data.addPlacePayload()); // given



    }

    @When("user calls AddPlace API with POST http request")
    public void userCallsAPIWithPOSTHttpRequest() {
        System.out.println("when");
        responsespecbuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = request // when
                .when().post("/maps/api/place/add/json")
                .then().spec(responsespecbuilder).extract().response();

    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int code) {
        System.out.println(response.getStatusCode());
        assertEquals(code, response.getStatusCode());

    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get(key).toString());
        assertEquals(value,jsonPath.get(key).toString());
    }
}


