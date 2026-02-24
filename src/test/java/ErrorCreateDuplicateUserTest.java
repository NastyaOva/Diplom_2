import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

public class ErrorCreateDuplicateUserTest extends BaseApiTest {

    Response actualResponse;

    @Before
    public void createUserTest() {
        UserModel userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
        actualResponse = UserStep.createUser(userModel);
    }

    @Test
    @DisplayName("Проверка ошибки создания пользователя, который уже существует")
    @Description("Проверка кода и тела ответа при попытке создать курьера, с параметрами уже существующего пользователя")
    public void checkErrorStatusCodeCreateDuplicateUserTest() {
        UserStep.checkStatusCodeUser(actualResponse, HTTP_FORBIDDEN);
        UserStep.checkFalseUser(actualResponse);
        UserStep.checkMessageErrorCreateUser(actualResponse, "User already exists");
    }
}
