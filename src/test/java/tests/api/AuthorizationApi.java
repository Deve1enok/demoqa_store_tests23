package tests.api;

import data.TestData;
import io.restassured.response.Response;
import models.AuthorizationRequestApi;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import tests.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.ParamUrlApi.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.ApiSpecification.requestSpec;
import static specs.ApiSpecification.responseAuthorization200;

public class AuthorizationApi extends BaseTest {

    AuthorizationRequestApi authorizationData = new AuthorizationRequestApi();
    TestData uData = new TestData();
    ProfilePage profData = new ProfilePage();

   @Test
   void successfulLoginWithApiTest() {
        authorizationData.setUserName(uData.getLogin());
        authorizationData.setPassword(uData.getPassword());

        Response authResponse  = given(requestSpec)
                .contentType(JSON)
                .body(authorizationData)

                .when()
                .post(USER_LOGIN_API_URL_PATH)

                .then()
                .spec(responseAuthorization200)
                .extract().response();

        open(FATEST_OPEN_URL_FROM_ICO);
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open(USER_PROFILE_URL_PATH);
        profData.userNameShouldHaveAuthLoginText();

    }
}
