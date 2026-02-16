import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestOrderData;
import test.models.OrderModel;
import test.steps.OrderStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class CreateOrderWithoutLoginPositiveTest extends BaseApiTest {

    OrderModel orderModel;
    Response responseOrder;

    @Before
    public void dataIngredients() {
        Response responseData = TestOrderData.getDataIngredients();
        TestOrderData.getAllIngredientsId(responseData);
        orderModel = new OrderModel(TestOrderData.generationIngredient(responseData));
        responseOrder = OrderStep.createOrderWithoutLogin(orderModel);
    }

    @Test
    @DisplayName("Проверка успешного создания заказа без авторизации")
    @Description("Проверка кода ответа при создании заказа")
    public void createOrderWithoutLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_OK);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа без авторизации")
    @Description("Проверка наличия true в теле ответа")
    public void checkSuccessOrderWithoutLoginTest() {
        OrderStep.checkSuccessOrder(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа без авторизации")
    @Description("Проверка наличия названия бургера в теле ответа при создании заказа без авторизации")
    public void checkNameBurgerIsNotNullWithoutLoginTest() {
        OrderStep.checkNameBurgerIsNotNull(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа без авторизации")
    @Description("Проверка наличия номера заказа в теле ответа при создании заказа без авторизации")
    public void checkNumberOrderIsNotNullWithoutLoginTest() {
        OrderStep.checkNumberOrderIsNotNull(responseOrder);
    }
}
