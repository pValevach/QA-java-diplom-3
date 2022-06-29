package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    public static final String ROOT = BASE_URL + "/login";

    //  Заголовок Вход.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement title;

    //  Поле Email.
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement fieldEmail;

    //  Поле Password.
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement fieldPassword;

    //  Кнопка Войти.
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement buttonEnter;

    @Step("Get text from title 'Вход'")
    public String getTitle() {
        return getTextFrom(title);
    }

    @Step("Login with the existing user and get to the constructor page")
    public ConstructorPage login(String email, String password) {
        waitForLoad(title);

        setValueToField(email,fieldEmail);
        setValueToField(password,fieldPassword);

        buttonEnter.click();

        return Selenide.page(ConstructorPage.class);
    }
}