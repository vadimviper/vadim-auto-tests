import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @Test
       public void shouldAuthorizeTest() {
        //1. Открыть страницу https://github.com/
        open("https://github.com/");
        //2. Кликнуть на кнопку sign in
        $("[href='/login']").click();
        //3. Заполнить инпуты логина и пароля
        $("[id='login_field']").sendKeys("v.habibrahmanov.kazanexpress@gmail.com");
        $("[id='password']").sendKeys("Vipervadim32");
        //4. Нажать кнопку sign in
        $(".js-sign-in-button").click();
        //5. Проверить авторизацию
        $(".Header").shouldBe(Condition.visible);
        //6. Нажать на меню профиля
        $("[aria-label='View profile and more']").click();
        //7. Нажать на профиль
        $(byText("Your profile")).click();
        //5. Проверить профиль
        $("[class='vcard-names ']").shouldBe(Condition.visible);

        sleep(3000);

    }
}
