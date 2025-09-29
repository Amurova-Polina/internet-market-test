package ibs.amurova.pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ibs.amurova.factory.CatalogueSection;
import ibs.amurova.pages.SearchResultsPage;
import ibs.amurova.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static ibs.amurova.common_elements.CommonElements.divClass;

public class CatalogueModalPage extends BasePage {

    private SelenideElement catalogueSectionsBlock = divClass("h_c_menu_container active")
            .as("Блок информации со списком подсекций (определенного) раздела каталога");

    private ElementsCollection sideCatalogueSections = $$x("//li[contains(@class, 'h_c_aside_item')]")
            .as("Боковые секции в модальном окне каталога");
    private ElementsCollection catalogueSubsections = catalogueSectionsBlock.$$x(".//div[contains(@class, 'h_c_menu_item_heading_name')]")
            .as("Подсекции (определенного) раздела каталога");


    @Step("Выбрать категорию \"{section}\" каталога")
    public CatalogueModalPage clickCatalogueSection(CatalogueCategory section) {
        sideCatalogueSections
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.has(text(section.getValue())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нужная секция не найдена"))
                .click();
        return this;
    }

    @Step("Выбрать подкатегорию \"{subsection}\"каталога")
    public SearchResultsPage clickCatalogueSubsection(CatalogueSection subsection) {
        catalogueSubsections
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.has(text(subsection.getValue())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нужная подсекция не найдена"))
                .click();
        return new SearchResultsPage();
    }

    @Step("Выбрать подкатегорию \"{subSubsection}\" в подкатегории \"{subsection}\"каталога")
    public SearchResultsPage clickSubsubsection(CatalogueSection subsection, String subSubsection) {
        catalogueSubsections
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.has(text(subsection.getValue())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нужная подсекция не найдена"))
                .$$x("./ancestor::nav/li")
                .asFixedIterable()
                .stream()
                .filter(x -> x.has(text(subSubsection)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нужный раздел подсекции не найден"))
                .click();

        return new SearchResultsPage();
    }

}
