import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import test.data.TestOrderData;
import test.data.TestUserData;
import test.models.OrderModel;
import test.models.UserModel;
import test.steps.OrderStep;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class CreateOrderWithLoginPositiveTest extends BaseApiTest {

    Response responseOrder;
    Response response;
    UserModel userModel;

    @Before
    public void dataIngredients() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
        response = UserStep.loginUser(userModel);
        Response responseData = TestOrderData.getDataIngredients();
        TestOrderData.getAllIngredientsId(responseData);
        OrderModel orderModel = new OrderModel(TestOrderData.generationIngredient(responseData));
        responseOrder = OrderStep.createOrderWithLogin(orderModel, response);
    }

    @Test
    @DisplayName("Проверка успешного создания заказа c авторизацией")
    @Description("Проверка кода и тела ответа при создании заказа")
    public void createOrderWithLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_OK);
        OrderStep.checkSuccessOrder(responseOrder);
        OrderStep.checkNameBurgerIsNotNull(responseOrder);
        OrderStep.checkNumberOrderIsNotNull(responseOrder);
        OrderStep.checkIngredientsIsNotNull(responseOrder);
        OrderStep.checkIdIsNotNull(responseOrder);
        OrderStep.checkUserName(responseOrder, userModel.getName());
        OrderStep.checkUserEmail(responseOrder, userModel.getEmail());
        OrderStep.checkStatusShouldBeDone(responseOrder);
        OrderStep.checkPriceOrderIsNotNull(responseOrder);
    }
}