package praktikum.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalAccountProfilePage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement logoutButton;

    @Step("Кнопкa выхода из системы")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Убедиться, что URL-адрес является страницей входа в систему")
    public void checkURLIsLoginPage() {
        webdriver().shouldHave(url(LoginPage.URL));
    }
}