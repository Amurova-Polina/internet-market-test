package ibs.amurova.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ibs.amurova.pages.abstraction.BasePage;
import ibs.amurova.pages.common.FilterCondition;
import ibs.amurova.pages.common.FilterOption;
import ibs.amurova.pages.common.PriceType;
import io.qameta.allure.Step;

import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.Wait;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultsPage extends BasePage {

    private final ElementsCollection productCards = $$x("//li[contains(@class, 'catalog_Level2__goods_list__item')]")
            .as("Карточки товаров в результатах поиска");
    private final ElementsCollection serchResultsArticuls = $$x(".//div[contains(@class, 'goods_card_articul')]")
            .as("Артикулы всех товаров в выдаче поиска");
    private final ElementsCollection filterOptions = $$x("//div[contains(@class, 'sortBlockWrap_item ')]")
            .as("Фильтры для сортировки результатов поиска");


    @Step("Найти товар по артикулу")
    public ProductPage findProductByArticul(String articul) {
        serchResultsArticuls
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.is(Condition.text(articul)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Товар с артикулом '%s'не найден".formatted(articul)))
                .$x("./../a[contains(@class, 'goods_card_text swiper-no-swiping')]")
                .click();
        return new ProductPage();
    }

    @Step("Найти товар по заголовку")
    public ProductPage clickProductByName(String name) {
        productCards.stream()
                .map(result -> result.$x(".//a[contains(@title, '" + name + "')]"))
                .filter(el -> name.equals(el.getAttribute("title")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Не найден элемент с title = " + name))
                .click();
        return new ProductPage();
    }

    @Step("Установить фильтр \"{filter}\" в значение \"{condition}\"")
    public SearchResultsPage setFilter(FilterOption filter, FilterCondition condition) {
        List<String> textsBefore = productCards
                .shouldBe(sizeGreaterThan(0))
                .asFixedIterable()
                .stream()
                .map(SelenideElement::getText)
                .toList();

        SelenideElement chosenFilter = filterOptions
                .asFixedIterable()
                .stream()
                .filter(x -> x.is(visible))
                .filter(x -> x.has(attribute("data-name", filter.getValue())))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Фильтр '" + filter.getValue() + "' не найден или не виден"));
        chosenFilter.click();

        if (!chosenFilter
                .shouldHave(cssClass("active"))
                .has(attribute("data-subname", condition.getElementAttribute()))) {
            chosenFilter.click();
        }

        Wait().until(driver -> {
            List<String> textsAfter = productCards
                    .shouldBe(sizeGreaterThan(0))
                    .asDynamicIterable()
                    .stream()
                    .map(SelenideElement::getText)
                    .toList();
            return !textsAfter.equals(textsBefore);
        });

        return this;
    }

    @Step("Проверить результаты сортировки по цене ({priceType}) в порядке \"{condition}\"")
    public SearchResultsPage checkPriceFilterResults(FilterCondition condition, PriceType priceType) {
        List<Double> priceValues = productCards
                .shouldBe(sizeGreaterThan(0))
                .asFixedIterable()
                .stream()
                .filter(card -> card.$x(priceType.getXpath()).is(visible))
                .map(card -> card.$x(priceType.getXpath()).getText())
                .filter(price -> !price.isEmpty())
                .map(value -> value.replace(",", "."))
                .map(Double::valueOf)
                .toList();

        List<Double> sortedPriceValues = priceValues.stream()
                .sorted(
                        condition == FilterCondition.ASCENDING
                                ? Comparator.naturalOrder()
                                : Comparator.reverseOrder()
                )
                .toList();

        assertEquals(priceValues, sortedPriceValues, "Список не отсортирован в порядке: " + condition + " для типа цены: " + priceType);
        return this;
    }

    @Step("Убедиться, что данный товар единственный в списке")
    public SearchResultsPage assertResultIsSingle() {
        productCards.shouldHave(size(1));
        return this;
    }

}