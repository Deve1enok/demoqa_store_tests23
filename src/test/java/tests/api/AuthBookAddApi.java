package tests.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.AuthorizationRequestApi;
import models.BookAddedResponseApi;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import tests.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.ParamUrlApi.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.ApiSpecification.*;

@Tag("store_test")
public class AuthBookAddApi extends BaseTest {
    ProfilePage profData = new ProfilePage();
    AuthorizationRequestApi authorizationData = new AuthorizationRequestApi();
    TestData tData = new TestData();


    @Test
    @Tag("positive_test")
    void authAddBookApi(){
        SelenideLogger.addListener("allure", new AllureSelenide());

    step("Отправляем POST запрос", () -> {

        String dataBook = "{\"userId\":\"0c0bfe1b-f0d2-4b76-92a8-a31ea8215110\",\"collectionOfIsbns\":[{\"isbn\":\"9781449325862\"}]}";


        authorizationData.setUserName(tData.getLogin());
        authorizationData.setPassword(tData.getPassword());

        Response authResponse  = given(requestSpec)
                .contentType(JSON)
                .body(authorizationData)

                .when()
                .post(USER_LOGIN_API_URL_PATH)

                .then()
                .spec(responseAuthorization200)
                .extract().response();


        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .queryParams("UserId", authResponse.path("userId"))

                .when()
                .delete(USER_ADD_BOOK_URL_PATH)

                .then()
                .spec(responseBookDelete204)
                .extract().response();


        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(dataBook)

                .when()
                .post(USER_ADD_BOOK_URL_PATH)

                .then()
                .spec(responseBookAdded201)
                .extract().as(BookAddedResponseApi.class);

        open(FATEST_OPEN_URL_FROM_ICO);
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open(USER_PROFILE_URL_PATH);
        profData.disableBanner();
        profData.profileShouldHaveBookFromTable();



});
    }
}
