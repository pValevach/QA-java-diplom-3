import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.ConstructorPage;
import pojo.User;
import pojo.UserCredentials;
import rest.UserClient;

import static org.apache.http.HttpStatus.SC_OK;

public class LogoutFromAccountPageTest {

    private UserCredentials credentials;
    private UserClient userClient;
    private String accessToken;

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
    @DisplayName("Positive logout from the account page by button 'Выйти'")
    public void logoutFromAccountPageByButton() {
        String expectedResult = "Вход";
        String actualResult = Selenide.open(ConstructorPage.BASE_URL, ConstructorPage.class)
                .goToLoginPage()
                .login(credentials.getEmail(), credentials.getPassword())
                .goToAccountPage()
                .logout()
                .getTitle();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
