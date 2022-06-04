import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AuthTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPages.mainPage.mainSignInButton()
                .click();
    }

    @Test
    @DisplayName("Успешная атворизация")
    public void shouldAuthorizeTest() {
        TestPages.mainPage.loginField()
                .sendKeys("v.habibrahmanov.kazanexpress@gmail.com");
        TestPages.mainPage.passwordField()
                .sendKeys("Vipervadim32");
        TestPages.mainPage.loginButton()
                .click();
        TestPages.mainPage.headerBanner()
                .shouldBe(visible);
        TestPages.mainPage.dropdownButton()
                .click();
        TestPages.mainPage.yourProfileButton()
                .click();
        TestPages.mainPage.headerName()
                .shouldBe(visible);
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} :{0}")
    @DisplayName("Авторизация с некорректными данными:")
    public void shouldNotAuthorizeTest(String type, String phone, String password){
        TestPages.mainPage.loginField()
                .sendKeys(phone);
        TestPages.mainPage.passwordField()
                .sendKeys(password);
        TestPages.mainPage.loginButton()
                .click();
        TestPages.mainPage.warningPopup()
                .shouldBe(visible);
    }
    static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                arguments(
                        "зарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9999999999",
                        "123456789Qq"
                ),
                arguments(
                        "незарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9100000000",
                        "123456789Qq"
                )
        );
    }
}