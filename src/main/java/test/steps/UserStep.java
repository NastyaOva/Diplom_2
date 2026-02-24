package test.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import test.models.UserModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static test.constants.UrlPath.*;

public class UserStep {

    @Step("Создание пользователя")
    public static Response createUser(UserModel userModel) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(CREATE_PATH_USER);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(UserModel userModel) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(userModel)
                .when()
                .post(LOGIN_PATH_USER);
    }

    @Step("Проверка кода ответа при отправке запроса")
    public static void checkStatusCodeUser(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code);
    }

    @Step("Проверка наличия true в теле ответа при создании пользователя")
    public static void checkSuccessUser(Response response) {
        response.then()
                .log().all()
                .body("success", equalTo(true));
    }

    @Step("Проверка email в теле ответа")
    public static void checkUserEmail(Response response, UserModel userModel) {
        response.then()
                .log().all()
                .body("user.email", equalTo(userModel.getEmail()));
    }

    @Step("Проверка name в теле ответа")
    public static void checkUserName(Response response, UserModel userModel) {
        response.then()
                .log().all()
                .body("user.name", equalTo(userModel.getName()));
    }

    @Step("Проверка наличия accessToken в теле при создании пользователя")
    public static void checkAccessTokenIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("accessToken", notNullValue());
    }

    @Step("Проверка наличия refreshToken в теле при создании пользователя")
    public static void checkRefreshTokenIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("refreshToken", notNullValue());
    }

    @Step("Получение токена accessToken")
    public static String accessToken(Response response) {
        return response.path("accessToken");
    }

    @Step("Получение токена refreshToken")
    public static String refreshToken(Response response) {
        return response.path("refreshToken");
    }

    @Step("Проверка наличия false в теле ответа при попытке создать пользователя")
    public static void checkFalseUser(Response response) {
        response.then()
                .log().all()
                .body("success", equalTo(false));
    }

    @Step("Проверка текста в поле message при попытке создать пользователя")
    public static void checkMessageErrorCreateUser(Response response, String expectedText) {
        response.then()
                .log().all()
                .body("message", equalTo(expectedText));
    }

    @Step("Удаление пользователя")
    public static void deleteUser(Response response) {
        given()
                .log().all()
                .header("Authorization", "Bearer" + accessToken(response))
                .delete(DELETE_PATH_USER);
    }
}