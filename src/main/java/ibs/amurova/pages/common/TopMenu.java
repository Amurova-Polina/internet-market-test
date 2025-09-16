package ibs.amurova.pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ibs.amurova.factory.ElementAttribute;
import ibs.amurova.factory.ElementTypeOption;
import ibs.amurova.factory.ElementsFactory;
import ibs.amurova.pages.SearchResultsPage;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static ibs.amurova.common_elements.CommonElements.divClass;

@Getter
public class TopMenu {

    private final SelenideElement cartIcon = new ElementsFactory()
            .getElementByType(ElementTypeOption.A, ElementAttribute.CLASS, "top_menu_link top_menu_basket_icon top_menu_button_modal")
            .as("Иконка корзины");

    private final SelenideElement cartRedCounterIcon = cartIcon.$("div.red_counter_icon.js-cart-quantity");
    private final SelenideElement catalogueButton = $x("//div[contains(@class, 'top_catalog_btn') and not(contains(@class, 'top_catalog_btn_icon'))]");

    private final SelenideElement catalogBlock = new ElementsFactory()
            .getElementByType(ElementTypeOption.DIV, ElementAttribute.CLASS, "top_menu_catalogBlock")
            .as("Подраздел топ меню 'каталог'");

    private final SelenideElement topMenuContainer = divClass("top_menu_container top_menu")
            .as("Контейнер со строкой поиска, 'Войти' и корзиной");

    private final SelenideElement searchInput = topMenuContainer
            .$x(".//input")
            .as("Поисковая строка");


    private final ElementsCollection catalogSections = catalogBlock
            .$$("a.top_menu_catalogBlock_listBlock_item_link")
            .as("Разделы каталога в верхнем меню");


    @Step("Перейти в корзину")
    public CartPage goToCart() {
        cartIcon.shouldBe(visible, Duration.ofSeconds(30)).click();
        return new CartPage();
    }

    @Step("Открыть подраздел \"{section.value}\" ")
    public SearchResultsPage chooseCatalogueSection(CatalogueCategory section) {
        catalogSections
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.has(text(section.getValue())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нужная секция не найдена"))
                .click();

        return new SearchResultsPage();
    }

    @Step("Ввести запрос \"{request}\" в поисковую строку")
    public SearchResultsPage searchRequest(String request) {
        searchInput.sendKeys(request);
        divClass("search_zoom_icon").click();
        return new SearchResultsPage();
    }

    @Step("Перейти в каталог")
    public CatalogueModalPage goToCatalogue() {
        catalogueButton.shouldBe(visible, Duration.ofSeconds(20)).click();
        return new CatalogueModalPage();
    }

}