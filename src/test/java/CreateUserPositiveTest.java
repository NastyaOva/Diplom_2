import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import test.data.TestUserData;
import test.models.UserModel;
import test.steps.UserStep;

import static java.net.HttpURLConnection.HTTP_OK;

public class CreateUserPositiveTest extends BaseApiTest {

    UserModel userModel;

    @Before
    public void createUser() {
        userModel = TestUserData.generationUser();
        this.responseUser = UserStep.createUser(userModel);
    }

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    @Description("Проверка кода и тела ответа при создании пользователя")
    public void checkCreateUserTest() {
        UserStep.checkStatusCodeUser(responseUser, HTTP_OK);
        UserStep.checkSuccessUser(responseUser);
        UserStep.checkUserEmail(responseUser, userModel);
        UserStep.checkUserName(responseUser, userModel);
        UserStep.checkAccessTokenIsNotNull(responseUser);
        UserStep.checkRefreshTokenIsNotNull(responseUser);
    }
}
