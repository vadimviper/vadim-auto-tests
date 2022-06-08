import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ProfileTest {

    @Test
    @DisplayName("Успешная атворизация")
    public void shouldAuthorizeTest2() {
        open("https://github.com/");
        TestPages.mainPage.mainSignInButton().click();
    }
}