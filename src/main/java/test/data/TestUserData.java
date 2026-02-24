package test.data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import test.models.UserModel;

import java.util.Locale;

public class TestUserData {
    public static Faker faker = new Faker(new Locale("ru"));

    public static UserModel user(String email, String password, String name) {
        return new UserModel(email, password, name);
    }

    @Step("Генерация пользователя")
    public static UserModel generationUser() {
        return user(faker.name().username() + "@yandex.ru", faker.internet().password(), faker.name().firstName());
    }

}