package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class JUnitPage {

    public SelenideElement repositoriesButton() {
        return $(byText("Repositories")).as("кнопка репозитория");
    }
    public SelenideElement jUnitButton() {
        return $("[href='/junit-team/junit4']").as("кнопка 'Юнита4'");
    }
    public SelenideElement dropdownJUnitButton() {
        return $(".btn.css-truncate").as("дропдаун веток");
    }
    public SelenideElement fixturesBranchButton() {
        return $("[href='https://github.com/junit-team/junit4/tree/fixtures']").as("ветка 'Фикстура'");
    }
    public SelenideElement releaseButton() {
        return $("[href='/junit-team/junit4/releases']").as("кнопка 'Релизы'");
    }
    public SelenideElement releaseSearch() {
        return $(".form-control.subnav-search-input.width-full").as("поле поиска 'Релиза");
    }
    public SelenideElement jUnitTeamImage() {
        return $("[alt='@junit-team']").as("изображение иконки 'ДжейЮнит'");
    }
    public SelenideElement jUnitText() {
        return $(".anchor").as("якорь текста 'Юнита4'");
    }
    public SelenideElement fixturesTitle() {
        return $(".btn.css-truncate").as("название ветки 'Фикстуры'");
    }
    public SelenideElement releasesPage() {
        return  $(".js-selected-navigation-item.selected.subnav-item").as("страница 'Релизы'");
    }
    public SelenideElement releasesBlock() {
        return $(".Box-footer").as("блок результата");
    }
}