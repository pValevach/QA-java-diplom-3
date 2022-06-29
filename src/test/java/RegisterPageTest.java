import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.RegisterPage;
import pojo.UserCredentials;
import rest.UserClient;

import static org.apache.http.HttpStatus.SC_OK;

public class RegisterPageTest {

    private UserClient userClient;
    private String accessToken;
    private String expectedResult;
    private String actualResult;

    @Before
    public void setup() {
        userClient = new UserClient();
    }

    @After
    public void teardown() {
        if (accessToken != null) {
            userClient.deleteUserBy(accessToken);
        }
    }

    @Test
    @DisplayName("Positive register user")
    public void isRegisterOk() {
        String name = RandomStringUtils.randomAlphanumeric(6);
        String email = RandomStringUtils.randomAlphanumeric(6) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(6);

        expectedResult = "Оформить заказ";
        actualResult = Selenide.open(RegisterPage.ROOT, RegisterPage.class)
                .registerCorrect(name, email, password)
                .login(email, password)
                .getButtonOrderText();

        UserCredentials credentials = new UserCredentials(email, password);
        accessToken = userClient.getAccessTokenFrom(userClient.loginBy(credentials)
                .assertThat()
                .statusCode(SC_OK));

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Check incorrect password length error exist")
    public void setIncorrectPasswordLength() {
        String password = RandomStringUtils.randomAlphanumeric(5);

        expectedResult = "Некорректный пароль";
        actualResult = Selenide.open(RegisterPage.ROOT, RegisterPage.class)
                .getIncorrectPasswordError(password);

        Assert.assertEquals(expectedResult, actualResult);
    }
}
