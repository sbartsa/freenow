import com.freenow.test.helper.API;
import com.freenow.test.helper.CoreHelper;
import com.freenow.test.helper.pageObjects.ApiHandler;
import com.freenow.test.helper.pageObjects.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by stevevarsanis on 27/07/19.
 *
 * Documentation: https://slite.com/api/s/note/CYibBMU2hGwUDuh3yobtPf/Suite-Documentation
 */
public class API_Validate_Schemas_Tests extends CoreHelper {
    private API api;
    private String endPoint,userId,postId;

    @BeforeClass
    public void initialise() {
        api = new API();
    }

    @Test(description = "Verify posts endpoint correct schema response")
    public void test1() {
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,null);
        Assertions.verifyJsonSchema(endPoint, "posts_json_schema.json");
    }

    @Test(description = "Verify specific post correct schema response")
    public void test2() {
        postId="1";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        Assertions.verifyJsonSchema(endPoint, "posts_json_schema_with_post_id.json");
    }

    @Test(description = "Verify posts endpoint correct schema response for user")
    public void test3() {
        userId = "1";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS_WITH_USER_ID, "/" + userId);
        Assertions.verifyJsonSchema(endPoint, "posts_json_schema_for_user.json");
    }

    @Test(description = "Verify comments endpoint correct schema response for user")
    public void test4() {
        endPoint = api.apiHandler().createURL(ApiHandler.API.COMMENTS, null);
        Assertions.verifyJsonSchema(endPoint, "comments_json_schema.json");
    }

    @Test(description = "Verify comments endpoint correct schema response for post")
    public void test5() {
        postId = "1";
        endPoint = api.apiHandler().createURL(ApiHandler.API.COMMENTS_WITH_POST_ID, userId);
        Assertions.verifyJsonSchema(endPoint, "comments_json_schema_for_post.json");
    }

}
