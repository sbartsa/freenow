package com.freenow.test.helper.pageObjects;

import com.freenow.test.helper.ConfigProperties;
import com.freenow.test.helper.Logger;


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


}
