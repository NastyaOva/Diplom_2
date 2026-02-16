import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.OrderModel;
import test.models.UserModel;
import test.steps.OrderStep;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class CreateOrderWithoutIngredientsWithLoginTest extends BaseApiTest {

    Response responseOrder;
    Response response;
    UserModel userModel;

    @Before
    public void dataIngredients() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
        response = UserStep.loginUser(userModel);
        OrderModel orderModel = new OrderModel(null);
        responseOrder = OrderStep.createOrderWithLogin(orderModel, response);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создания заказа без ингредиентов (c авторизацией)")
    @Description("Проверка кода ответа при попытке создания заказа без ингредиентов")
    public void createOrderWithoutIngredientsWithLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_BAD_REQUEST);
    }

    @Test
    @DisplayName("Проверка тела ответа при попытке создания заказа без ингредиентов (c авторизацией)")
    @Description("Проверка наличия false в теле ответа при попытке создания заказа без ингредиентов")
    public void checkFalseOrderWithoutIngredientsTest() {
        OrderStep.checkFalseOrder(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при попытке создания заказа без ингредиентов (c авторизацией)")
    @Description("Проверка текста ошибки при попытке создания заказа без ингредиентов")
    public void checkMessageErrorCreateOrderWithoutIngredientsTest() {
        OrderStep.checkMessageErrorCreateOrder(responseOrder,"Ingredient ids must be provided");
    }
}
