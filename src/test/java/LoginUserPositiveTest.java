import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginUserPositiveTest extends BaseApiTest {

    Response actualResponse;
    UserModel userModel;

    @Before
    public void loginUser() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
        actualResponse = UserStep.loginUser(userModel);
    }

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    @Description("Проверка кода и тела ответа при успешной авторизации пользователя")
    public void loginUserPositiveTest() {
        UserStep.checkStatusCodeUser(actualResponse, HTTP_OK);
        UserStep.checkSuccessUser(actualResponse);
        UserStep.checkUserEmail(actualResponse, userModel);
        UserStep.checkUserName(actualResponse, userModel);
        UserStep.checkAccessTokenIsNotNull(actualResponse);
        UserStep.checkRefreshTokenIsNotNull(actualResponse);
    }
}