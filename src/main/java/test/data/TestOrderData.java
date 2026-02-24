package test.data;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static test.constants.UrlPath.GET_PATH_INGREDIENTS;

public class TestOrderData {

    @Step("Получение данных об ингредиентах")
    public static Response getDataIngredients() {
        return given()
                .log().all()
                .get(GET_PATH_INGREDIENTS)
                .then()
                .extract().response();
    }

    @Step("Получение списка id ингредиентов")
    public static List<String> getAllIngredientsId(Response response) {
        return response.path("data._id");
    }

    @Step("Генерация ингредиентов для заказа")
    public static List<String> generationIngredient(Response response) {
        Random random = new Random();
        List<String> allId = getAllIngredientsId(response);
        String firstIngredient = allId.get(random.nextInt(allId.size()));
        String secondIngredient = allId.get(random.nextInt(allId.size()));
        return List.of(firstIngredient, secondIngredient);
    }
}
