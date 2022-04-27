package com.stepDefination;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        MyStepdefs stepdefs = new MyStepdefs();
        if(MyStepdefs.place_id ==null) {
            stepdefs.addPlacePayloadWithNameLanguageAddress("sp2", "bengali", "india");
            stepdefs.userCallsAPIWithHttpRequest("addPlaceAPI", "POST");
            stepdefs.verifyPlaceIDCreatedAboveMapsToIn("sp2", "getPlaceAPI");
        }
    }
}
