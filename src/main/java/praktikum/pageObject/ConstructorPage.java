package praktikum.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']/..")
    private SelenideElement saucesTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']/..")
    private SelenideElement bunsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsAnchor;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']/..")
    private SelenideElement fillingsTab;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;



    @Step("Нажать на вкладку Соусы")
    public void clickSauces() {
        saucesAnchor.click();
    }

    @Step("Проверить открытие вкладки Соусы")
    public void checkSaucesTabIsOpened() {
        saucesTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Нажать на вкладку Булки")
    public void clickBuns() {
        bunsAnchor.click();
    }

    @Step("Проверить открытие вкладки булки")
    public void checkBunsTabIsOpened() {
        bunsTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Нажать на вкладку Начинки")
    public void clickFillings() {
        fillingsAnchor.click();
    }

    @Step("Проверить открытие вкладки начинок")
    public void checkFillingsTabIsOpened() {
        fillingsTab.shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Нажать кнопку входа на главной странице")
    public void clickLoginButton() {
        loginButton.click();
    }

}