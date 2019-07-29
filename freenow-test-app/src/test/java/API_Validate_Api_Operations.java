import com.freenow.test.helper.API;
import com.freenow.test.helper.CoreHelper;
import com.freenow.test.helper.pageObjects.ApiHandler;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by stevevarsanis on 29/07/19.
 *
 * Documentation: https://slite.com/api/s/note/CYibBMU2hGwUDuh3yobtPf/Suite-Documentation
 */
public class API_Validate_Api_Operations extends CoreHelper {
    private API api;
    private String endPoint,postId;
    private String mockUrl = "https://my-json-server.typicode.com/sbartsa/freenow-stevevarsanis";


    @BeforeClass
    public void initialise() {
        api = new API();
    }

    @Test(description = "Verify post request returns 201")
    public void test1() throws IOException {
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,null);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        System.out.println("Path:" + this.getClass().getResource("/").getPath());

        String requestBody = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/").getPath()+ "jsonFiles/post.json")), "UTF-8");
        api.apiHandler().postRequest(endPoint, requestBody).then().statusCode(201);
    }

    @Test(description = "Verify put request returns 200 ")
    public void test2() throws IOException {
        postId="2";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        String requestBody = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/").getPath()+ "jsonFiles/put.json")), "UTF-8");
        api.apiHandler().putRequest(endPoint, requestBody).then().statusCode(200);
    }

    @Test(description = "Verify put request returns 404 if not found")
    public void test3() throws IOException {
        postId="4";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        String requestBody = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/").getPath()+ "jsonFiles/put.json")), "UTF-8");
        api.apiHandler().putRequest(endPoint, requestBody).then().statusCode(404);
    }

    @Test(description = "Verify patch request returns 200 ")
    public void test4() throws IOException {
        postId="3";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        String requestBody = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/").getPath()+ "jsonFiles/patch.json")), "UTF-8");
        api.apiHandler().patchRequest(endPoint, requestBody).then().statusCode(200);

    }

    @Test(description = "Verify patch request returns 404 if not found")
    public void test5() throws IOException {
        postId="5";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        String requestBody = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("/").getPath()+ "jsonFiles/patch.json")), "UTF-8");
        api.apiHandler().patchRequest(endPoint, requestBody).then().statusCode(404);
    }

    @Test(description = "Verify delete request returns 200")
    public void test6() {
        postId="2";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        api.apiHandler().deleteRequest(endPoint).then().statusCode(200);
    }

    @Test(description = "Verify delete request returns 404 if not found")
    public void test7() {
        postId="5";
        endPoint = api.apiHandler().createURL(ApiHandler.API.POSTS,"/" + postId);
        //Override the base url with the mocked one
        endPoint = endPoint.replace(api.apiHandler().url ,mockUrl);
        api.apiHandler().deleteRequest(endPoint).then().statusCode(404);

    }

}
