package com.stepDefination;

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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    RequestSpecification request;
    ResponseSpecification responsespecbuilder;
    Response response;

    @Given("Add Place Payload")
    public void addPlacePayload() {

        System.out.println("hello");

        //body creation using POJO class and serialization
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setName("ricky new home");
        addPlace.setPhone_number("123456789");

        List<String> myListTypes = new ArrayList<String>();
        myListTypes.add("shoe park");
        myListTypes.add("shop");
        addPlace.setTypes(myListTypes);

        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        addPlace.setLocation(loc);

        RequestSpecification requestspecbuilder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();// given

        request = given().spec(requestspecbuilder).body(addPlace); // given

        responsespecbuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }

    @When("user calls AddPlace API with POST http request")
    public void userCallsAPIWithPOSTHttpRequest() {
        System.out.println("ricky");
        response = request // when
                .when().post("/maps/api/place/add/json")
                .then().spec(responsespecbuilder).extract().response();

    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int arg0) {
        System.out.println(response.getStatusCode());
        assertEquals(200, response.getStatusCode());

    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get(key).toString());
        assertEquals(value,jsonPath.get(key).toString());
    }
}


