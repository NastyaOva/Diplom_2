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
    @Description("Проверка кода ответа при успешной авторизации пользователя")
    public void loginUserPositiveTest() {
        UserStep.checkStatusCodeUser(actualResponse, HTTP_OK);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешной авторизации пользователя")
    @Description("Проверка наличия true в теле ответа")
    public void checkSuccessUserLoginTest() {
        UserStep.checkSuccessUser(actualResponse);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешной авторизации пользователя")
    @Description("Проверка соответствия email в запросе и ответе при авторизации пользователя")
    public void checkUserLoginEmailTest() {
        UserStep.checkUserEmail(actualResponse, userModel);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешной авторизации пользователя")
    @Description("Проверка соответствия name в запросе и ответе при авторизации пользователя")
    public void checkUserLoginNameTest() {
        UserStep.checkUserName(actualResponse, userModel);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешной авторизации пользователя")
    @Description("Проверка наличия accessToken в теле ответа при авторизации пользователя")
    public void checkAccessTokenIsNotNullLoginUserTest() {
        UserStep.checkAccessTokenIsNotNull(actualResponse);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешной авторизации пользователя")
    @Description("Проверка наличия refreshToken в теле ответа при авторизации пользователя")
    public void checkRefreshTokenIsNotNullLoginUserTest() {
        UserStep.checkRefreshTokenIsNotNull(actualResponse);
    }
}