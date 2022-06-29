package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage extends BasePage {

    //  Заголовок Соберите бургер.
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement title;

    //  Заголовок Булки.
    @FindBy(how = How.XPATH, using = ".//div[@style='display: flex;']/div[1]")
    private SelenideElement titleBuns;

    //  Подзаголовок Булки.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement subtitleBuns;

    //  Заголовок Соусы.
    @FindBy(how = How.XPATH, using = ".//div[@style='display: flex;']/div[2]")
    private SelenideElement titleSauce;

    //  Подзаголовок Соусы.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement subtitleSauce;

    //  Заголовок Начинки.
    @FindBy(how = How.XPATH, using = ".//div[@style='display: flex;']/div[3]")
    private SelenideElement titleFilling;

    //  Подзаголовок Начинки.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement subtitleFilling;

    //  Кнопка Войти в аккаунт.
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLogin;

    //  Кнопка Оформить заказ.
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement buttonOrder;

    @Step("Go to the login page by button 'Войти в аккаунт' when unauthorized")
    public LoginPage goToLoginPage() {
        buttonLogin.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Get text from the button 'Оформить заказ'")
    public String getButtonOrderText() {
        return getTextFrom(buttonOrder);
    }

    @Step("Get text from the title 'Булки'")
    public String getTitleBunsText() {
        titleFilling.click();
        titleBuns.click();
        return getTextFrom(subtitleBuns);
    }

    @Step("Get text from the title 'Соусы'")
    public String getTitleSauceText() {
        titleSauce.click();
        return getTextFrom(subtitleSauce);
    }

    @Step("Get text from the title 'Начинки'")
    public String getTitleFillingText() {
        titleFilling.click();
        return getTextFrom(subtitleFilling);
    }
}