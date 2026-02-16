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
    @Description("Проверка кода ответа при попытке создания пользователя без заполнения поля email")
    public void checkErrorStatusCodeCreateUserWithoutEmailTest() {;
        UserStep.checkStatusCodeUser(firstResponse, HTTP_FORBIDDEN);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля email")
    @Description("Проверка наличия false в теле ответа при попытке создания пользователя без заполнения поля email")
    public void checkFalseCreateUserWithoutEmailTest() {
        UserStep.checkFalseUser(firstResponse);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля email")
    @Description("Проверка текста ошибки при попытке создания пользователя")
    public void checkMessageErrorCreateUserWithoutEmailTest() {
        UserStep.checkMessageErrorCreateUser(firstResponse, "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создать пользователя без поля password")
    @Description("Проверка кода ответа при попытке создания пользователя без заполнения поля password")
    public void checkErrorStatusCodeCreateUserWithoutPasswordTest() {;
        UserStep.checkStatusCodeUser(secondResponse, HTTP_FORBIDDEN);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля password")
    @Description("Проверка наличия false в теле ответа при попытке создания пользователя без заполнения поля password")
    public void checkFalseCreateUserWithoutPasswordTest() {
        UserStep.checkFalseUser(secondResponse);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля password")
    @Description("Проверка текста ошибки при попытке создания пользователя")
    public void checkMessageErrorCreateUserWithoutPasswordTest() {
        UserStep.checkMessageErrorCreateUser(secondResponse, "Email, password and name are required fields");
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создать пользователя без поля name")
    @Description("Проверка кода ответа при попытке создания пользователя без заполнения поля name")
    public void checkErrorStatusCodeCreateUserWithoutNameTest() {;
        UserStep.checkStatusCodeUser(thirdResponse, HTTP_FORBIDDEN);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля name")
    @Description("Проверка наличия false в теле ответа при попытке создания пользователя без заполнения поля name")
    public void checkFalseCreateUserWithoutNameTest() {
        UserStep.checkFalseUser(thirdResponse);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании пользователя без поля name")
    @Description("Проверка текста ошибки при попытке создания пользователя")
    public void checkMessageErrorCreateUserWithoutNameTest() {
        UserStep.checkMessageErrorCreateUser(thirdResponse, "Email, password and name are required fields");
    }
}