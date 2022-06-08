import io.qameta.allure.Owner;
import io.qameta.allure.Feature;
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
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("vipervadim")
@Feature("Проверка репозиториев 'ЮнитТим'")
public class JUnitTest {

    @BeforeEach
    public void setup(){
        step("Открыть сайт https://github.com/junit-team", () -> {
        open("https://github.com/junit-team");
        TestPages.JUnitPage.repositoriesButton()
                .click();
        TestPages.JUnitPage.jUnitTeamImage()
                .shouldBe(visible);
        });
    }

    @Test
    @Story("Проверка репозитория 'Юнит4'")
    @DisplayName("Выбор ветки")
    public void selectionBranchFixtures() {
        step("Открыть репозиторий 'ДжейЮнит4'", () -> {
        TestPages.JUnitPage.jUnitButton()
                .click();
        TestPages.JUnitPage.jUnitText()
                .shouldBe(visible);
        });

        step("Открыть дропдаун и переключиться на ветку 'Фикстура'", () -> {
        TestPages.JUnitPage.dropdownJUnitButton()
                .click();
        TestPages.JUnitPage.fixturesBranchButton()
                .click();
        });

        step("Проверить, что произошло переключение на ветку 'Фикстура'", () -> {
        TestPages.JUnitPage.fixturesTitle()
                .shouldBe(visible);
        });
    }

    @MethodSource("searchBy")
    @ParameterizedTest(name = "{displayName} :{0}")
    @Story("Проверка релизов в репозитории 'Юнит4'")
    @DisplayName("Позитивня проверка поиска по релизам в репозитории")
    public void positiveSearch(String type, String searchData, String searchResults){
        step("Открыть репозиторий 'ДжейЮнит4'", () -> {
        TestPages.JUnitPage.jUnitButton()
                .click();
        TestPages.JUnitPage.jUnitText()
                .shouldBe(visible);
        });

        step("Перейти в раздел 'Релизы'", () -> {
        TestPages.JUnitPage.releaseButton()
                .click();
        TestPages.JUnitPage.releasesPage()
                .shouldBe(visible);
        });

        step("Ввести данные в поле и произвести поиск", () -> {
        TestPages.JUnitPage.releaseSearch()
                .sendKeys(searchData + Keys.ENTER);
        });

        step("Проверить, что поиск прошёл успешно", () -> {
        TestPages.JUnitPage.releasesBlock()
                .shouldHave(text(searchResults));
        });
    }

    static Stream<Arguments> searchBy() {
        return Stream.of(
                arguments(
                        "фрагмету из цифр",
                        "4.13",
                        "JUnit 4.13"
                ),

                arguments(
                        "фрагмету из букв",
                        "RC",
                        "JUnit 4.13 RC"

                ),

                arguments(
                        "полному названию",
                        "JUnit 4.13 RC 2",
                        "JUnit 4.13 RC 2"
                )
        );
    }
}