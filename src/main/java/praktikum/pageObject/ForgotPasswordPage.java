package praktikum.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Восстановить']")
    private SelenideElement submitButton;

    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    @Step("Нажать на ссылку 'Восстановить пароль'")
    public void clickLoginLink() {
        loginLink.click();
    }
}
