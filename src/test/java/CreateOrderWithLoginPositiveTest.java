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
    @Description("Проверка кода ответа при создании заказа")
    public void createOrderWithLoginTest() {
        OrderStep.checkStatusCodeOrder(responseOrder, HTTP_OK);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия true в теле ответа")
    public void checkSuccessOrderWithLoginTest() {
        OrderStep.checkSuccessOrder(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия названия бургера в теле ответа при создании заказа c авторизацией")
    public void checkNameBurgerIsNotNullWithLoginTest() {
        OrderStep.checkNameBurgerIsNotNull(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия номера заказа в теле ответа при создании заказа c авторизацией")
    public void checkNumberOrderIsNotNullWithLoginTest() {
        OrderStep.checkNumberOrderIsNotNull(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия ингредиентов в теле ответа при создании заказа с авторизацией")
    public void checkIngredientsIsNotNullTest() {
        OrderStep.checkIngredientsIsNotNull(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия id заказа в теле ответа при создании заказа с авторизацией")
    public void checkIdIsNotNullTest() {
        OrderStep.checkIdIsNotNull(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия имени пользователя в теле ответа при создании заказа с авторизацией")
    public void checkUserNameTest() {
        OrderStep.checkUserName(responseOrder, userModel.getName());
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия почты пользователя в теле ответа при создании заказа с авторизацией")
    public void checkUserEmailTest() {
        OrderStep.checkUserEmail(responseOrder, userModel.getEmail());
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка статуса заказа в теле ответа при создании заказа с авторизацией")
    public void checkStatusShouldBeDoneTest() {
        OrderStep.checkStatusShouldBeDone(responseOrder);
    }

    @Test
    @DisplayName("Проверка тела ответа при успешном создании заказа c авторизацией")
    @Description("Проверка наличия суммы заказа в теле ответа при создании заказа с авторизацией")
    public void checkPriceOrderIsNotNullTest() {
        OrderStep.checkPriceOrderIsNotNull(responseOrder);
    }
}