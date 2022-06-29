package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage extends BasePage {

    //  Кнопка Выход.
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement buttonExit;

    //  Подсказка В этом разделе вы можете изменить свои персональные данные.
    @FindBy(how = How.CLASS_NAME, using = "Account_text__fZAIn")
    private SelenideElement prompt;

    @Step("Get text from the prompt 'В этом разделе вы можете изменить свои персональные данные'")
    public String getPromptText() {
        return getTextFrom(prompt);
    }

    @Step("Logout from the account and get to the login page")
    public LoginPage logout() {
        buttonExit.click();
        return Selenide.page(LoginPage.class);
    }
}