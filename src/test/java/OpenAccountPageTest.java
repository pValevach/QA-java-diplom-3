import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.LoginPage;
import pojo.User;
import pojo.UserCredentials;
import rest.UserClient;

import static org.apache.http.HttpStatus.SC_OK;

public class OpenAccountPageTest {

    private UserCredentials credentials;
    private UserClient userClient;
    private String accessToken;
    private final String expectedResult = "В этом разделе вы можете изменить свои персональные данные";
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
        Selenide.closeWindow();
        userClient.deleteUserBy(accessToken);
    }

    @Test
    @DisplayName("Positive switch to the account page from the construction page")
    public void openAccountPageFromConstructorPage() {
        actualResult = Selenide.open(LoginPage.ROOT, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToAccountPage()
                .getPromptText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Positive switch to the account page from the feed page")
    public void openAccountPageFromFeedPage() {
        actualResult = Selenide.open(LoginPage.ROOT, LoginPage.class)
                .login(credentials.getEmail(), credentials.getPassword())
                .goToFeedPage()
                .waitForLoad()
                .goToAccountPage()
                .getPromptText();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
