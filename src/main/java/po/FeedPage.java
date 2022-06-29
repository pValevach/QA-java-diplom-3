package po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FeedPage extends BasePage {

    public static final String ROOT = BASE_URL + "/feed";

    //  Заголовок Лента заказов.
    @FindBy(how = How.XPATH, using = ".//h1[text()='Лента заказов']")
    private SelenideElement title;

    public FeedPage waitForLoad() {
        waitForLoad(title);
        return Selenide.page(FeedPage.class);
    }
}
