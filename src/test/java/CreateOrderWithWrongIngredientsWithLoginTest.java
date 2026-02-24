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

import java.util.List;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class CreateOrderWithWrongIngredientsWithLoginTest extends BaseApiTest {

    Response responseOrder;

    @Before
    public void dataIngredients() {
        UserModel userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
        Response response = UserStep.loginUser(userModel);
        OrderModel orderModel = new OrderModel(List.of("0000000"));
        responseOrder = OrderStep.createOrderWithLogin(orderModel, response);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создания заказа с невалидным хэшем ингредиента (c авторизацией)")
    @Description("Проверка кода ответа при попытке создания заказа с невалидным хэшем ингредиента")
    public void createOrderWithoutIngredientsWithLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_INTERNAL_ERROR);
    }
}