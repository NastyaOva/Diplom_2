package test.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import test.models.OrderModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static test.constants.UrlPath.CREATE_PATH_ORDER;
import static test.steps.UserStep.accessToken;

public class OrderStep {

    @Step("Создание заказа без авторизации")
    public static Response createOrderWithoutLogin(OrderModel orderModel) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(orderModel)
                .post(CREATE_PATH_ORDER);
    }

    @Step("Создание заказа с авторизацией")
    public static Response createOrderWithLogin(OrderModel orderModel, Response response) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer" + accessToken(response))
                .body(orderModel)
                .post(CREATE_PATH_ORDER);
    }

    @Step("Проверка кода ответа при отправке запроса")
    public static void checkStatusCodeOrder(Response response, int code) {
        response.then()
                .log().all()
                .statusCode(code);
    }

    @Step("Проверка наличия true в теле ответа при создании заказа")
    public static void checkSuccessOrder(Response response) {
        response.then()
                .log().all()
                .body("success", equalTo(true));
    }

    @Step("Проверка наличия названия бургера в теле ответа при создании заказа")
    public static void checkNameBurgerIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("name", notNullValue());
    }

    @Step("Проверка наличия номера заказа в теле ответа при создании заказа")
    public static void checkNumberOrderIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("order.number", notNullValue());
    }

    @Step("Проверка наличия ингредиентов в теле ответа при создании заказа с авторизацией")
    public static void checkIngredientsIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("order.ingredients", notNullValue());
    }

    @Step("Проверка наличия id заказа в теле ответа при создании заказа с авторизацией")
    public static void checkIdIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("order._id", notNullValue());
    }

    @Step("Проверка наличия имени пользователя в теле ответа при создании заказа с авторизацией")
    public static void checkUserName(Response response, String expectedText) {
        response.then()
                .log().all()
                .body("order.owner.name", equalTo(expectedText));
    }

    @Step("Проверка наличия почты пользователя в теле ответа при создании заказа с авторизацией")
    public static void checkUserEmail(Response response, String expectedText) {
        response.then()
                .log().all()
                .body("order.owner.email", equalTo(expectedText));
    }

    @Step("Проверка статуса заказа в теле ответа при создании заказа с авторизацией")
    public static void checkStatusShouldBeDone(Response response) {
        response.then()
                .log().all()
                .body("order.status", equalTo("done"));
    }

    @Step("Проверка наличия суммы заказа в теле ответа при создании заказа с авторизацией")
    public static void checkPriceOrderIsNotNull(Response response) {
        response.then()
                .log().all()
                .body("order.price", notNullValue());
    }

    @Step("Проверка наличия false в теле ответа при попытке создать заказ")
    public static void checkFalseOrder(Response response) {
        response.then()
                .log().all()
                .body("success", equalTo(false));
    }

    @Step("Проверка текста в поле message при попытке создать заказ")
    public static void checkMessageErrorCreateOrder(Response response, String expectedText) {
        response.then()
                .log().all()
                .body("message", equalTo(expectedText));
    }
}
