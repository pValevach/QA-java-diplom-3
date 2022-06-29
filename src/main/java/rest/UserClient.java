package rest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.User;
import pojo.UserCredentials;

import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class UserClient extends RestClient {

    private final String ROOT = "/auth";
    private final String REGISTER = ROOT + "/register";
    private final String LOGIN = ROOT + "/login";
    private final String USER = ROOT + "/user";

    @Step("Get accessToken")
    public String getAccessTokenFrom(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }

    @Step("Send POST request to api/auth/register and save response")
    public ValidatableResponse register(User user) {
        return reqSpec
                .body(user).log().all()
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    @Step("Send POST request to api/auth/login and save response")
    public ValidatableResponse loginBy(UserCredentials credentials) {
        return reqSpec
                .body(credentials).log().all()
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    @Step("Send DELETE request to api/auth/user w/ accessToken and check 202 status code from response")
    public void deleteUserBy(String token) {
        reqSpec
                .auth().oauth2(getPureTokenFrom(token))
                .when()
                .delete(USER)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }

    public String getPureTokenFrom(String token) {
        return token.substring(7);
    }
}

