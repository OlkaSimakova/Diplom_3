package praktikum.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class HeaderPage {

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    private SelenideElement logo;

    @FindBy(how = How.XPATH, using = ".//nav/a[@href='/account']")
    private SelenideElement personalAccountButton;

    @FindBy(how = How.XPATH, using = ".//li[1]/a[@href='/']")
    private SelenideElement constructorButton;

    // добавила, надо посмотреть тест для него
    @FindBy(how = How.XPATH, using = ".//nav/ul/li/a[@href='/feed']")
    private SelenideElement orderFeedButton;


    @Step("Нажать на кнопку 'Личный кабинет' в шапке страницы")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Проверить, что URL-адрес является профилем на странице учетной записи")
    public void checkURLIsProfilePage() {
        webdriver().shouldHave(url(PersonalAccountProfilePage.URL));
    }

    @Step("Проверить, что URL-адрес является домашней страницей")
    public void checkURlIsHomepage() {
        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    public void checkButtonHasHrefToAccount() {
        personalAccountButton.shouldHave(Condition.href("/account"));
    }

    @Step("Нажать на кнопку конструктора")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        logo.click();
    }
}