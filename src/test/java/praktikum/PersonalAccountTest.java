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
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountTest {

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
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();
        webdriver().shouldHave(url(ConstructorPage.URL));

        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickPersonalAccountButton();
        webdriver().shouldHave(url(PersonalAccountProfilePage.URL));
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
    @DisplayName("Выход из системы на странице личного кабинета")
    @Description("Вошедший в систему пользователь должен иметь возможность выйти из системы")
    public void logoutFromPersonalAccountButton() {
        PersonalAccountProfilePage personalAccountProfilePage = page(PersonalAccountProfilePage.class);
        personalAccountProfilePage.clickLogoutButton();
        personalAccountProfilePage.checkURLIsLoginPage();
    }

    @Test
    @DisplayName("Нажав кнопку создания, пользователь перенаправляется из личного кабинета на домашнюю страницу")
    public void constructorButtonRedirectsFromPersonalAccountToHomepage() {
        HeaderPage headerPage = page(HeaderPage.class);

        headerPage.clickConstructorButton();
        headerPage.checkURlIsHomepage();
    }

    @Test
    @DisplayName("Нажав на логотип, пользователь перенаправляется из личного кабинета на домашнюю страницу")
    public void logoRedirectsFromPersonalAccountToHomepage() {
        HeaderPage headerPage = page(HeaderPage.class);

        headerPage.clickLogo();
        headerPage.checkURlIsHomepage();
    }

}