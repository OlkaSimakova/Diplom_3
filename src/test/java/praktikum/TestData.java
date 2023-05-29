package praktikum;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import praktikum.userDeletion.User;

public class TestData {

    @Step("Заполнить случайными данными для тестирования")
    public User getRandomUserTestData() {
        final String name = RandomStringUtils.randomAlphabetic(4);
        final String email = RandomStringUtils.randomAlphabetic(4).toLowerCase() + "@mail.ru";
        final String password = RandomStringUtils.randomAlphabetic(8);

        return User.builder().name(name).email(email).password(password).build();
    }

}
