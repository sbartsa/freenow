package com.freenow.test.helper.pageObjects;

import com.freenow.test.helper.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Assertions {
    private static Logger logger;

    public static void verifyIsValidEmail(String email){
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        assert email.matches(emailRegex);
        Logger.pass("OK --> Email: " + email + " is valid");
    }

    public static void verifyJsonSchema(String endpoint, String jsonToValidateFromResources) {
        ApiHandler api = new ApiHandler();
        api.getRequest(endpoint).then().body(matchesJsonSchemaInClasspath("jsonSchemas/" + jsonToValidateFromResources));
        Logger.pass("Ok--> Json Schema '" + jsonToValidateFromResources +"' was valid");
    }
}
