package page;

import com.codeborne.selenide.SelenideElement;
import data.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    TestData uData = new TestData();
    private final SelenideElement userNameFieldAfterAuth = $("#userName-value");
    private final SelenideElement  bookTableResult = $(".ReactTable");
    private final SelenideElement  userNameLoginField = $("#userName");
    private final SelenideElement  passwordLoginField = $("#password");


    public ProfilePage userNameShouldHaveAuthLoginText(){
        userNameFieldAfterAuth.shouldHave(text(uData.getLogin()));
        return this;
    }
    public ProfilePage profileShouldHaveBookFromTable(){
        bookTableResult.shouldHave(text(uData.getGitBook()));
        return this;
    }
    public ProfilePage setLoginAuth(){
        userNameLoginField.setValue(uData.getLogin());
        return this;
    }
    public ProfilePage setPasswordAuth(){
        passwordLoginField.setValue(uData.getPassword()).pressEnter();
        return this;
    }
}
