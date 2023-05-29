package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.pageObject.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    @Test
    @DisplayName("При открытии страницы открывается раздел 'Булочки'")
    public void checkWhenOpenHomePageOpenBunsSection() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.checkBunsTabIsOpened();
    }

    @Test
    @DisplayName("При нажатии на соусы список переходит в раздел Соусы")
    public void checkWhenClickOnSaucesOpenSaucesSection() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);

        constructorPage.clickSauces();
        constructorPage.checkSaucesTabIsOpened();
    }

    @Test
    @DisplayName("При нажатии на начинки список переходит в раздел 'Начинки'")
    public void checkWhenClickOnFillingsOpenFillingsSection() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);

        constructorPage.clickFillings();
        constructorPage.checkFillingsTabIsOpened();
    }

}
