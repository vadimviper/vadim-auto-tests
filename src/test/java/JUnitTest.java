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
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JUnitTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/junit-team");
        TestPages.JUnitPage.repositoriesButton()
                .click();
        TestPages.JUnitPage.jUnitTeamImage()
                .shouldBe(visible);
    }

    @Test
    @DisplayName("Выбор ветки")
    public void selectionBranchFixtures() {
        TestPages.JUnitPage.jUnitButton()
                .click();
        TestPages.JUnitPage.jUnitText()
                .shouldBe(visible);
        TestPages.JUnitPage.dropdownJUnitButton()
                .click();
        TestPages.JUnitPage.fixturesBranchButton()
                .click();
        TestPages.JUnitPage.fixturesTitle()
                .shouldBe(visible);
    }

    @MethodSource("searchBy")
    @ParameterizedTest(name = "{displayName} :{0}")
    @DisplayName("Позитивня проверка поиска по релизам в репозитории")
    public void positiveSearch(String type, String searchData, String searchResults){
        TestPages.JUnitPage.jUnitButton()
                .click();
        TestPages.JUnitPage.jUnitText()
                .shouldBe(visible);
        TestPages.JUnitPage.releaseButton()
                .click();
        TestPages.JUnitPage.releasesPage()
                .shouldBe(visible);
        TestPages.JUnitPage.releaseSearch()
                .sendKeys(searchData + Keys.ENTER);
        TestPages.JUnitPage.releasesBlock()
                .shouldHave(text(searchResults));
    }

    static Stream<Arguments> searchBy() {
        return Stream.of(
                arguments(
                        "фрагмету из цифр",
                        "4.13",
                        "6"
                ),

                arguments(
                        "фрагмету из букв",
                        "RC",
                        "2"

                ),

                arguments(
                        "полному названию",
                        "JUnit 4.13 RC 2",
                        "1"
                )
        );
    }
}