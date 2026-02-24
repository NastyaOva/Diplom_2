import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

public class CreateUserWithoutFieldTest extends BaseApiTest {

    Response firstResponse;
    Response secondResponse;
    Response thirdResponse;

    @Before
    public void createUsersTest() {
        UserModel firstUserModel = TestUserData.generationUser();
        firstUserModel.setEmail(null);
        firstResponse = UserStep.createUser(firstUserModel);
        UserModel secondUserModel = TestUserData.generationUser();
        secondUserModel.setPassword(null);
        secondResponse = UserStep.createUser(secondUserModel);
        UserModel thirdUserModel = TestUserData.generationUser();
        thirdUserModel.setName(null);
        thirdResponse = UserStep.createUser(secondUserModel);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создать пользователя без поля email")
    @Description("Проверка кода и тела ответа при попытке создания пользователя без заполнения поля email")
    public void checkErrorStatusCodeCreateUserWithoutEmailTest() {;
        UserStep.checkStatusCodeUser(firstResponse, HTTP_FORBIDDEN);
        UserStep.checkFalseUser(firstResponse);
        UserStep.checkMessageErrorCreateUser(firstResponse, "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создать пользователя без поля password")
    @Description("Проверка кода и тела ответа при попытке создания пользователя без заполнения поля password")
    public void checkErrorStatusCodeCreateUserWithoutPasswordTest() {;
        UserStep.checkStatusCodeUser(secondResponse, HTTP_FORBIDDEN);
        UserStep.checkFalseUser(secondResponse);
        UserStep.checkMessageErrorCreateUser(secondResponse, "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создать пользователя без поля name")
    @Description("Проверка кода и тела ответа при попытке создания пользователя без заполнения поля name")
    public void checkErrorStatusCodeCreateUserWithoutNameTest() {;
        UserStep.checkStatusCodeUser(thirdResponse, HTTP_FORBIDDEN);
        UserStep.checkFalseUser(thirdResponse);
        UserStep.checkMessageErrorCreateUser(thirdResponse, "Email, password and name are required fields");
    }
}