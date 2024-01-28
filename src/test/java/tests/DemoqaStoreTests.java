package tests;

import org.junit.jupiter.api.Test;
import page.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static data.ParamUrlApi.USER_LOGIN_UI_URL_PATH;


public class DemoqaStoreTests extends BaseTest {
    ProfilePage profData = new ProfilePage();

    @Test
    public void successfulLoginWithUiTest() {

        open(USER_LOGIN_UI_URL_PATH);
        profData.setLoginAuth();
        profData.setPasswordAuth();
        profData.userNameShouldHaveAuthLoginText();


    }}
