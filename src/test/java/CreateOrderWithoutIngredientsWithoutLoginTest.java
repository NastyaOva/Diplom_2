import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.models.OrderModel;
import test.steps.OrderStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;


public class CreateOrderWithoutIngredientsWithoutLoginTest extends BaseApiTest {

    OrderModel orderModel;
    Response responseOrder;

    @Before
    public void dataIngredients() {
        orderModel = new OrderModel(null);
        responseOrder = OrderStep.createOrderWithoutLogin(orderModel);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создания заказа без ингредиентов (без авторизации)")
    @Description("Проверка кода ответа при попытке создания заказа без ингредиентов")
    public void createOrderWithoutIngredientsWithoutLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка тела ответа при попытке создания заказа без ингредиентов (без авторизации)")
    @Description("Проверка наличия false в теле ответа при попытке создания заказа без ингредиентов")
    public void checkFalseOrderWithoutIngredientsTest() {
        OrderStep.checkFalseOrder(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при попытке создания заказа без ингредиентов (без авторизации)")
    @Description("Проверка текста ошибки при попытке создания заказа без ингредиентов")
    public void checkMessageErrorCreateOrderWithoutIngredientsTest() {
        OrderStep.checkMessageErrorCreateOrder(responseOrder,"Ingredient ids must be provided");
    }
}