package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage extends BasePage {
    public static final String ROOT = BASE_URL + "/register";

    //  Заголовок Регистрация.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private SelenideElement title;

    //  Поле Name.
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div/input")
    private SelenideElement fieldName;

    //  Поле Email.
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div/input")
    private SelenideElement fieldEmail;

    //  Поле Password.
    @FindBy(how = How.XPATH, using = ".//fieldset[3]/div/div/input")
    private SelenideElement fieldPassword;

    //  Элемент Некорректный пароль.
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement inputError;

    //  Кнопка Зарегистрироваться.
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegister;

    //  Кнопка Войти.
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    @Step("Go to the login page by button 'Войти' from the register page")
    public LoginPage goToLoginPage() {
        buttonLogin.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Register the new user and return to the login page")
    public LoginPage registerCorrect(String name, String email, String password) {
        waitForLoad(title);

        setValueToField(name, fieldName);
        setValueToField(email, fieldEmail);
        setValueToField(password, fieldPassword);

        buttonRegister.click();

        return Selenide.page(LoginPage.class);
    }

    @Step("Get the incorrect password length error")
    public String getIncorrectPasswordError(String password) {
        waitForLoad(title);

        setValueToField(password, fieldPassword);
        fieldEmail.click();

        return getTextFrom(inputError);
    }
}