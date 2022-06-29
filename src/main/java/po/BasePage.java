package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class BasePage {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    //  Элемент Личный кабинет.
    @FindBy(how = How.XPATH, using = ".//a/p[text()='Личный Кабинет']")
    private SelenideElement account;

    //  Элемент Лента заказов.
    @FindBy(how = How.XPATH, using = ".//a[@href='/feed']")
    private SelenideElement feed;

    //  Элемент Конструктор.
    @FindBy(how = How.XPATH, using = ".//li/a[@href='/']")
    private SelenideElement constructor;

    //  Логотип Stellar Burgers.
    @FindBy(how = How.XPATH, using = ".//a[@href='/']")
    private SelenideElement logo;

    public void navigateTo(SelenideElement element) {
        element.click();
    }

    @Step("Wait for element should be visible")
    public void waitForLoad(SelenideElement element) {
        element.shouldBe(visible);
    }

    @Step("Set value to the field")
    public void setValueToField(String value, SelenideElement element) {
        element.setValue(value);
    }

    public String getTextFrom(SelenideElement element) {
        return element.should(appear).getText();
    }

    @Step("Go to the account page by element 'Личный кабинет' when authorized")
    public AccountPage goToAccountPage(){
        navigateTo(account);
        return Selenide.page(AccountPage.class);
    }

    @Step("Go to the login page by element 'Личный кабинет' when unauthorized")
    public LoginPage goToLoginPage(){
        navigateTo(account);
        return Selenide.page(LoginPage.class);
    }

    @Step("Go to the feed page by element 'Лента заказов'")
    public FeedPage goToFeedPage(){
        navigateTo(feed);
        return Selenide.page(FeedPage.class);
    }

    @Step("Go to the constructor page by element 'Конструктор'")
    public ConstructorPage goToConstructorPage(){
        navigateTo(constructor);
        return Selenide.page(ConstructorPage.class);
    }

    @Step("Go to the constructor page by element 'Stellar Burgers'")
    public ConstructorPage goToBasePage(){
        navigateTo(logo);
        return Selenide.page(ConstructorPage.class);
    }
}