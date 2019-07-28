package com.freenow.test.helper.pageObjects;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import com.freenow.test.helper.ConfigProperties;
import com.freenow.test.helper.Logger;


import static com.jayway.restassured.RestAssured.given;


public class ApiHandler {

    Logger logger;
    private String url;
    private static String usersRoute = "/users";
    private static String postsRoute = "/posts";
    private static String commentsRoute = "/comments";
    private static String commentsWithPostIdRoute = "/comments?postId=";
    private static String postsWithUserId = "/posts?userId=";


    public enum API {
        USERS(usersRoute),
        POSTS(postsRoute),
        COMMENTS(commentsRoute),
        COMMENTS_WITH_POST_ID(commentsWithPostIdRoute),
        POSTS_WITH_USER_ID(postsWithUserId);

        private   String myRoute;
        API(String route){
            myRoute = route;
        }
        public String get(){
            return myRoute;
        }
    }

    public ApiHandler() {
        logger = new Logger();
        ConfigProperties configProperties = new ConfigProperties();
        url = configProperties.getPropertyValue(ConfigProperties.APPLICATION_URL);
    }


    /*
     * Create a custom URL based on the available routes and params
     */
    public String createURL(API route, String params) {
        String urlString = url + route.get();
        if (params != null) {
            urlString += params;
        }
        logger.info("Created url connection string: " + urlString);
        return urlString;
    }


    private Response getRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return  given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }


}
