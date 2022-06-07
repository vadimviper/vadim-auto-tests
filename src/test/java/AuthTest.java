import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("vipervadim")
@Feature("Авторизация")
public class AuthTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPages.mainPage.mainSignInButton()
                .click();
    }

    @Test
    @Story("Успешная авторизация")
    @DisplayName("Авторизация с корректными логином и паролем")
    public void shouldAuthorizeTest() {
        step("Ввести логин и пароль и нажать кнопку 'Войти'",() -> {
        TestPages.mainPage.loginField()
                .sendKeys("v.habibrahmanov.kazanexpress@gmail.com");
        TestPages.mainPage.passwordField()
                .sendKeys("Vipervadim32");
        TestPages.mainPage.loginButton()
                .click();
        });

        step("Проверить, что верхний баннер отображается", () -> {
        TestPages.mainPage.headerBanner()
                .shouldBe(visible);
        });


        step("Открыть дропдаун и кликнуть на кнопку 'Your profile'", () -> {
        TestPages.mainPage.dropdownButton()
                .click();
        TestPages.mainPage.yourProfileButton()
                .click();
        });


        step("Проверить, что отображается заголовок с именем", () -> {
        TestPages.mainPage.headerName()
                .shouldBe(visible);
    });
    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} :{0}")
    @Story("Авторизация с некорректными данными")
    @DisplayName("Авторизация с некорректными данными:")
    public void shouldNotAuthorizeTest(String type, String phone, String password){
        step("Ввести некорректные данные", () -> {
        TestPages.mainPage.loginField()
                .sendKeys(phone);
        TestPages.mainPage.passwordField()
                .sendKeys(password);
        TestPages.mainPage.loginButton()
                .click();
        });

        step("Авторизация не проходит. Отображается сообщение об ошибке", () -> {
        TestPages.mainPage.warningPopup()
                .shouldBe(visible);
        });
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