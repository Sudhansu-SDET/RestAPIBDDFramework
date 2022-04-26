package Resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {

    public static RequestSpecification requestSpecification() throws FileNotFoundException {

        PrintStream logFile = new PrintStream(new FileOutputStream("logging.txt"));

        RestAssured.baseURI="https://rahulshettyacademy.com";
        RequestSpecification requestspecbuilder = new RequestSpecBuilder().setBaseUri(RestAssured.baseURI)
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(logFile))
                .addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .setContentType(ContentType.JSON).build();// given

        return requestspecbuilder;
    }

}
