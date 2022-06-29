import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import po.ConstructorPage;

public class ConstructorPageTest {

    private String expectedResult;
    private String actualResult;

    @Test
    @DisplayName("Positive switch to the bun section")
    public void switchToBunSection() {
        expectedResult = "Булки";
        actualResult = Selenide.open(ConstructorPage.BASE_URL, ConstructorPage.class)
                .getTitleBunsText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive switch to the sauce section")
    public void switchToSauceSection() {
        expectedResult = "Соусы";
        actualResult = Selenide.open(ConstructorPage.BASE_URL, ConstructorPage.class)
                .getTitleSauceText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive switch to the filling section")
    public void switchToFillingSection() {
        expectedResult = "Начинки";
        actualResult = Selenide.open(ConstructorPage.BASE_URL, ConstructorPage.class)
                .getTitleFillingText();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
