import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.BasePage;
import po.ConstructorPage;
import po.PasswordRecoveryPage;
import po.RegisterPage;
import pojo.User;
import pojo.UserCredentials;
import rest.UserClient;

import static org.apache.http.HttpStatus.SC_OK;

public class LoginPageTest {

    private UserCredentials credentials;
    private UserClient userClient;
    private String accessToken;
    private final String expectedResult = "Оформить заказ";
    private String actualResult;

    @Before
    public void setup() {
        userClient = new UserClient();

        User user = User.getRandom();
        credentials = UserCredentials.getCredsFrom(user);

        accessToken = userClient.getAccessTokenFrom(userClient.register(user)
                .assertThat()
                .statusCode(SC_OK));
    }

    @After
    public void teardown() {
        userClient.deleteUserBy(accessToken);
    }

    @Test
    @DisplayName("Positive login with the existing user from the constructor page by button 'Войти в аккаунт'")
    public void loginFromConstructorPageByButton() {
        actualResult = Selenide.open(ConstructorPage.BASE_URL, ConstructorPage.class)
                .goToLoginPage()
                .login(credentials.getEmail(), credentials.getPassword())
                .getButtonOrderText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive login with the existing user from the constructor page by element 'Личный кабинет'")
    public void loginFromConstructorPageByAccount() {
        actualResult = Selenide.open(BasePage.BASE_URL, BasePage.class)
                .goToLoginPage()
                .login(credentials.getEmail(), credentials.getPassword())
                .getButtonOrderText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive login with the existing user from the register page by button 'Войти'")
    public void loginFromRegisterPageByButton() {
        actualResult = Selenide.open(RegisterPage.ROOT, RegisterPage.class)
                .goToLoginPage()
                .login(credentials.getEmail(), credentials.getPassword())
                .getButtonOrderText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive login with the existing user from the password recovery page by button 'Войти'")
    public void loginFromPasswordRecoveryPageByButton() {
        actualResult = Selenide.open(PasswordRecoveryPage.ROOt, PasswordRecoveryPage.class)
                .waitForLoad()
                .goToLoginPage()
                .login(credentials.getEmail(), credentials.getPassword())
                .getButtonOrderText();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
