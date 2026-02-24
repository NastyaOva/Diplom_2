import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class LoginUserNegativeTest extends BaseApiTest {

    UserModel userModel;

    @Before
    public void createUser() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке авторизовать пользователя с неверной почтой")
    @Description("Проверка кода и тела ответа при попытке авторизовать пользователя с неверной почтой")
    public void loginUserWithWrongEmailTest() {
        userModel.setEmail("wrong@yandex.ru");
        Response response = UserStep.loginUser(userModel);
        UserStep.checkStatusCodeUser(response, HTTP_UNAUTHORIZED);
        UserStep.checkFalseUser(response);
        UserStep.checkMessageErrorCreateUser(response, "email or password are incorrect");
    }

    @Test
    @DisplayName("Проверка ошибки при попытке авторизовать пользователя с неверным паролем")
    @Description("Проверка кода и тела ответа при попытке авторизовать пользователя с неверным паролем")
    public void loginUserWithWrongPasswordTest() {
        userModel.setPassword("00000");
        Response response = UserStep.loginUser(userModel);
        UserStep.checkStatusCodeUser(response, HTTP_UNAUTHORIZED);
        UserStep.checkFalseUser(response);
        UserStep.checkMessageErrorCreateUser(response, "email or password are incorrect");
    }
}
