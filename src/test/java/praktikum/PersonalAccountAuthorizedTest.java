package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.pageObject.ConstructorPage;
import praktikum.pageObject.HeaderPage;
import praktikum.pageObject.LoginPage;
import praktikum.pageObject.RegisterPage;
import praktikum.userDeletion.User;
import praktikum.userDeletion.UserClient;
import praktikum.userDeletion.UserCredentials;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.pageObject.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountAuthorizedTest {

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
    @DisplayName("Кнопка 'Личный кабинет' перенаправляет на страницу 'Личный кабинет' после авторизации пользователя")
    @Description("Создайте нового пользователя и войдите в систему. Нажмите на кнопку 'Личный кабинет'. Он перенаправляет на страницу профиля")
    public void personalAccountButtonRedirectsToPersonalAccount() {
        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.checkButtonHasHrefToAccount();
        headerPage.clickPersonalAccountButton();
        headerPage.checkURLIsProfilePage();
    }

}