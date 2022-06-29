package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage extends BasePage {

    public static final String ROOt = BASE_URL + "/forgot-password";

    //  Заголовок Восстановление пароля.
    @FindBy(how = How.XPATH, using = ".//h2[text()='Восстановление пароля']")
    private SelenideElement title;

    //  Кнопка Войти.
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement buttonLogin;

    public PasswordRecoveryPage waitForLoad() {
        waitForLoad(title);
        return Selenide.page(PasswordRecoveryPage.class);
    }

    @Step("Go to the login page by button 'Войти' when unauthorized")
    public LoginPage goToLoginPage() {
        buttonLogin.click();
        return Selenide.page(LoginPage.class);
    }
}