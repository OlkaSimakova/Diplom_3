package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.pageObject.*;
import praktikum.userDeletion.User;
import praktikum.userDeletion.UserClient;
import praktikum.userDeletion.UserCredentials;
import site.nomoreparties.stellarburgers.pageObject.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginTest {

    TestData testData = new TestData();
    String name;
    String email;
    String password;

    @Before
    public void setUp() {
        User user = testData.getRandomUserTestData();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();

        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
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
    @DisplayName("Войти в систему с правильными данными")
    @Description("Пользователь регистрируется в системе. Вход в систему. После успешного входа в систему пользователь будет удаляется")
    public void loginFromHomepage() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Войдите в систему с правильными данными, нажав кнопку личный кабинет")
    public void loginFromPersonalAccountButton() {
        HeaderPage headerPage = open(ConstructorPage.URL, HeaderPage.class);

        headerPage.clickPersonalAccountButton();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Войти в систему с правильными данными, нажав на ссылку для входа на странице регистрации")
    public void loginFromLinkOnRegistrationPage() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.clickLoginLink();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Войти в систему с правильными данными, нажав на ссылку для входа на странице забыли пароль")

    public void loginFromLinkOnForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.URL, ForgotPasswordPage.class);

        forgotPasswordPage.clickLoginLink();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }
}
