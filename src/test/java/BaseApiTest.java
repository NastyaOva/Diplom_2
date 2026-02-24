import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;
import test.steps.UserStep;

import static test.data.TestBaseData.BASE_URL;

public class BaseApiTest {
    protected Response responseUser;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @After
    public void tearDown() {
        if (responseUser != null) {
            UserStep.deleteUser(responseUser);
        }
    }
}