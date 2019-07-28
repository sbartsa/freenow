import com.freenow.test.helper.API;
import com.freenow.test.helper.CoreHelper;
import com.freenow.test.helper.dto.Comment;
import com.freenow.test.helper.dto.Post;
import com.freenow.test.helper.dto.User;
import com.freenow.test.helper.pageObjects.ApiHandler;
import com.freenow.test.helper.pageObjects.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by stevevarsanis on 27/07/19.
 */
public class API_Tests extends CoreHelper {
    private API api;
    private String endPoint;
    private List<User> users;
    private List<Post> posts;
    private List<Comment> comments;
    private User samantha;

    @BeforeClass
    public void initialise() {
        api = new API();
        samantha = new User();
    }

    @Test(description = "Verify user Samantha has been found")
    public void test1() {
        endPoint = api.apiHandler().createURL(ApiHandler.API.USERS,null);
        String userName = "Samantha";

        users = api.apiHandler().getUsers(endPoint);
        samantha.setUsername(userName);
        samantha = users.stream().filter(user ->
                                samantha.getUsername()
                                        .equals(user.getUsername()))
                                        .distinct()
                                        .findAny().get();

    }

    @Test(description = "Verify comments have valid email format", dependsOnMethods = "test1")
    public void test2() {

        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS_WITH_USER_ID, String.valueOf(samantha.getId()));
        posts = api.apiHandler().getPosts(endPoint);

        posts.forEach(post ->
        {
            try {
                endPoint = api.apiHandler().createURL(ApiHandler.API.COMMENTS_WITH_POST_ID, String.valueOf(post.getId()));
                comments = api.apiHandler().getComments(endPoint);
                for (Comment comment : comments) {
                    Assertions.verifyIsValidEmail(comment.getEmail());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}
