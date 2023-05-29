package praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pageObject.ConstructorPage;
import praktikum.pageObject.HeaderPage;
import praktikum.pageObject.LoginPage;
import site.nomoreparties.stellarburgers.pageObject.*;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountWithoutAuthorization {

    @Test
    @DisplayName("Personal Account button redirects to Login Page when user is not authorized")
    public void personalAccountButtonRedirectsToLogin() {
        HeaderPage headerPage = Selenide.open(ConstructorPage.URL, HeaderPage.class);

        headerPage.checkButtonHasHrefToAccount();
        headerPage.clickPersonalAccountButton();
        webdriver().shouldHave(url(LoginPage.URL));
    }

}
