package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification requestspecbuilder;

    public static RequestSpecification requestSpecification() throws IOException {

        if(requestspecbuilder==null){
        PrintStream logFile = new PrintStream(new FileOutputStream("logging.txt"));
        requestspecbuilder = new RequestSpecBuilder().setBaseUri(getPropertyValue("baseURL"))
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(logFile))
                .addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                .setContentType(ContentType.JSON).build();// given

        return requestspecbuilder;
        }
        return requestspecbuilder;

    }

    public static String getPropertyValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream("C://Users//sudha//IdeaProjects//RestAPIBDDFramework//src//test//java//Resources/global.properties");
        prop.load(fs);

        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key);
    }

}
