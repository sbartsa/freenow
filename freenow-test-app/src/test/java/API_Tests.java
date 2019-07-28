import com.freenow.test.helper.API;
import com.freenow.test.helper.CoreHelper;
import com.freenow.test.helper.dto.User;
import com.freenow.test.helper.pageObjects.ApiHandler;
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


}
