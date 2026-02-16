import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.models.OrderModel;
import test.steps.OrderStep;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class CreateOrderWithWrongIngredientsWithoutLoginTest extends BaseApiTest {

    Response responseOrder;

    @Before
    public void dataIngredients() {
        OrderModel orderModel = new OrderModel(List.of("0000000"));
        responseOrder = OrderStep.createOrderWithoutLogin(orderModel);
    }

    @Test
    @DisplayName("Проверка ошибки при попытке создания заказа с невалидным хэшем ингредиента (без авторизации)")
    @Description("Проверка кода ответа при попытке создания заказа с невалидным хэшем ингредиента")
    public void createOrderWithoutIngredientsWithoutLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_INTERNAL_ERROR);
    }
}
