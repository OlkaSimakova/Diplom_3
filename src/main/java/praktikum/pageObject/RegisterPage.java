package praktikum.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class RegisterPage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = ".//fieldset[1]/.//input[@type='text']")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//fieldset[2]/.//input[@type='text']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorIncorrectPasswordMessage;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Такой пользователь уже существует']")
    private SelenideElement errorExistingUserMessage;

    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    @Step("Ввод имени")
    public void setNameField(String name) {
        nameField.shouldBe(visible);
        nameField.setValue(name);
    }

    @Step("Ввод электронного адреса")
    public void setEmailField(String email) {
        emailField.shouldBe(visible);
        emailField.setValue(email);
    }

    @Step("Ввод пароля")
    public void setPasswordField(String password) {
        passwordField.shouldBe(visible);
        passwordField.setValue(password);
    }

    @Step("Зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Появляется ли сообщение об ошибке с неправильным паролем, если длина пароля меньше шести цифр")
    public void checkIncorrectPasswordErrorMessage() {
        errorIncorrectPasswordMessage.shouldBe(visible).shouldHave(text("Некорректный пароль"));
    }

    @Step("Сообщение об ошибке неправильного пароля не появляется, если длина пароля составляет шесть и более цифр")
    public void checkIncorrectPasswordMessageDoesNotAppear() {
        errorIncorrectPasswordMessage.shouldNotBe(visible);
    }

    @Step("Сообщение об ошибке существующего пользователя не появляется при нажатии на кнопку регистрации")
    public void checkExistingUserMessageDoesNotAppear() {
        errorExistingUserMessage.shouldNotBe(visible);
    }

    @Step("ссылка для входа на странице регистрации")
    public void clickLoginLink() {
        loginLink.shouldHave(href("/login"));
        loginLink.click();
    }

    @Step("зарегистрироваться")
    public void setRegistrationForm(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

}