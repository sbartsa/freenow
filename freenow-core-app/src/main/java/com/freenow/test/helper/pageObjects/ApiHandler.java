package com.freenow.test.helper.pageObjects;

import com.freenow.test.helper.ConfigProperties;
import com.freenow.test.helper.Logger;
import com.freenow.test.helper.dto.Comment;
import com.freenow.test.helper.dto.Post;
import com.freenow.test.helper.dto.User;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public Response getRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return  given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    /*
      Get the users from the relevant endpoint as a List
    */
    public List<User> getUsers(String endpoint) {
        List<User> usersList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(getRequest(endpoint).body().prettyPrint());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            usersList.add(User.mapJsonToUser(jsonObject));
        }
        return usersList;
    }

    /*
    Get the post from the relevant endpoint as a List
 */
    public List<Post> getPosts(String endpoint) {
        List<Post> postsList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(getRequest(endpoint).body().prettyPrint());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            postsList.add(Post.mapJsonToPost(jsonObject));
        }
        return postsList;
    }

    /*
        Get the post from the relevant endpoint as a List
     */
    public List<Comment> getComments(String endpoint) {
        List<Comment> commentsList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(getRequest(endpoint).body().prettyPrint());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            commentsList.add(Comment.mapJsonToComment(jsonObject));
        }
        return commentsList;
    }

}
