import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class CreateUserPositiveTest extends BaseApiTest {

    UserModel userModel;

    @Before
    public void createUser() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
    }

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    @Description("Проверка кода ответа при создании пользователя")
    public void checkCreateUserTest() {
        UserStep.checkStatusCodeUser(responseUser, HTTP_OK);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании пользователя")
    @Description("Проверка наличия true в теле ответа")
    public void checkSuccessUserTest() {
        UserStep.checkSuccessUser(responseUser);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании пользователя")
    @Description("Проверка соответствия email в запросе и ответе при создании пользователя")
    public void checkUserEmailTest() {
        UserStep.checkUserEmail(responseUser, userModel);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании пользователя")
    @Description("Проверка соответствия name в запросе и ответе при создании пользователя")
    public void checkUserNameTest() {
        UserStep.checkUserName(responseUser, userModel);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании пользователя")
    @Description("Проверка наличия accessToken в теле ответа при создании пользователя")
    public void checkAccessTokenIsNotNullTest() {
        UserStep.checkAccessTokenIsNotNull(responseUser);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании пользователя")
    @Description("Проверка наличия refreshToken в теле ответа при создании пользователя")
    public void checkRefreshTokenIsNotNullTest() {
        UserStep.checkRefreshTokenIsNotNull(responseUser);
    }
}
