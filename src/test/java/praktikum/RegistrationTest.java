package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.pageObject.LoginPage;
import praktikum.pageObject.RegisterPage;
import praktikum.userDeletion.User;
import praktikum.userDeletion.UserClient;
import praktikum.userDeletion.UserCredentials;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationTest {

    TestData testData = new TestData();
    String name;
    String email;
    String password;
    final String password5digits = "12345";
    final String password6digits = "123456";

    @Before
    public void setUp() {
        User user = testData.getRandomUserTestData();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
    }

    @After
    public void tearDown() {
        UserClient userClient = new UserClient();
        UserCredentials credentials = UserCredentials.builder().email(email).password(password).build();

        ValidatableResponse loginResponse = userClient.loginWithCorrectCredentials(credentials);
        int responseStatusCode = loginResponse.extract().statusCode();

        if (responseStatusCode == 200) {
            String token = loginResponse.extract().path("accessToken").toString().substring(7);
            userClient.delete(token);
        }
    }

    @Test
    @DisplayName("Пользователь регистрируется в системе с правильными данными")
    @Description("Регистрируем нового пользователя. Проверяем, что сообщения об ошибках не появлялись и после успешной регистрации перенаправляемся на страницу входа в систему. После тестирования пользователь будет удален.")
    public void userCanRegisterWithCorrectData() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordMessageDoesNotAppear();
        registerPage.checkExistingUserMessageDoesNotAppear();
        webdriver().shouldHave(url(LoginPage.URL));
    }

    @Test
    @DisplayName("Сообщение об ошибке с неправильным паролем появляется, когда пользователь пытается зарегистрироваться с 5 цифрами в пароле")
    @Description("Убедиться, что после нажатия на кнопку отправки появляется сообщение об ошибке. После тестирования пользователь будет удален.")
    public void errorMessageAppearWhenPassword5Digits() {
        password = password5digits;
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordErrorMessage();
    }

    @Test
    @DisplayName("Корректный ввод пароля")
    @Description("Успешное нажатия на кнопку отправки.После тестирования пользователь будет удален.")
    public void errorMessageDoesNotAppearWhenPassword6Digits() {
        password = password6digits;
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordMessageDoesNotAppear();
    }

}