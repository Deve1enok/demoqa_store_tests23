package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import page.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static data.ParamUrlApi.USER_LOGIN_UI_URL_PATH;

@Tag("store_test")
public class DemoqaStoreTests extends BaseTest {
    ProfilePage profData = new ProfilePage();

    @Test
    @Tag("positive_test")
    public void successfulLoginWithUiTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(USER_LOGIN_UI_URL_PATH);
        profData.setLoginAuth();
        profData.setPasswordAuth();
        profData.userNameShouldHaveAuthLoginText();


    }}
