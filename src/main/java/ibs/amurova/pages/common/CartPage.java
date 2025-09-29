package ibs.amurova.pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ibs.amurova.pages.base.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ibs.amurova.common_elements.CommonElements.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPage extends BasePage {


    private SelenideElement cartAdditionalBlock = divClass("cart_slider_header")
            .as("Блок с дополнительными товарами");
    private SelenideElement finalSumm = $x("//div[contains(@class, 'catalog__goods__blockwithdots total')]/div[contains(@class, 'catalog__goods__blockwithdots__value')]")
            .as("Итоговая сумма");
    private SelenideElement naturalPersonIcon = $x("//input[contains(@id, 'cart_private')]/..")
            .as("Иконка 'Физ. лицо'");
    private SelenideElement legalEntityIcon = $x("//input[contains(@id, 'cart_company')]/..")
            .as("Иконка 'Юр. лицо'");
    private SelenideElement clearCartButton = divClass("shopping_cart_action_item shopping_cart_action_clear")
            .as("Кнопка 'Очистить корзину'");

    private ElementsCollection itemsInCart = $$x("//div[contains(@class, 'item-row')]")
            .as("Товары, добавленные в корзину");
    private ElementsCollection itemsInCartNames = $$x("//a[contains(@class, 'title')]")
            .as("Названия товаров в корзине");
    private ElementsCollection itemsCost = $$x("//div[contains(@class, 'shopping_cart_goods_list_item_sum_item')]")
            .as("Цены товаров в корзине");

    private SelenideElement additionalGoodsSection(String text) {
        return cartAdditionalBlock.$x(".//div[contains(text(), '" + text + "')]");
    }

    private int calculateSummNaturalPerson() {
        return itemsCost
                .texts()
                .stream()
                .map(s -> s.replaceAll("\\D+", ""))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    @Step("Проверить, что в корзине \"{amount}\" товаров")
    public CartPage checkAmountInCart(int amount) {
        itemsInCart.shouldHave(size(amount), Duration.ofSeconds(10))
                .as("В корзине не '" + amount + "' товаров");
        return this;
    }

    @Step("Добавить в корзину товар \"{goodName}\" из раздела \"{section}\"")
    public CartPage addItemFromSection(String goodName, String section) {
        additionalGoodsSection(section).click();
        additionalGoodsSection((section))
                .$x("./../..//li//a[contains(@title, '" + goodName + "')]")
                .scrollIntoView(false)
                .shouldBe(visible)
                .$x("./../../..//div[contains(@class, 'goods_card_cart_block')]")
                .click();

        return this;
    }

    @Step("Проверить, что список товаров в корзине совпадает с ранее введенными запросами")
    public CartPage checkItemsName(List<String> expectedTexts) {

        List<String> actualTexts = itemsInCartNames.texts();

        assertTrue(expectedTexts.stream()
                        .allMatch(expected -> actualTexts.stream().anyMatch(actual -> actual.contains(expected)))
                , "В корзину добавлен не тот товар." +
                        "\nОжидаемые подстроки: " + expectedTexts +
                        "\nФактические тексты элементов: " + actualTexts);
        return this;
    }

    @Step("Проверить, что итоговая сумма соответствует сумме товаров")
    public CartPage checkSummNaturalPerson() {
        assertEquals(calculateSummNaturalPerson(), Integer.parseInt(finalSumm.text().replace(" ", "")), "Итоговая сумма рассчитана неправильно\n" +
                "Ожидалось: " + calculateSummNaturalPerson() +
                "\nФактически: " + Integer.parseInt(finalSumm.text().replace(" ", "")));
        return this;
    }

    @Step("Проверить, что сумма для юрлиц ниже")
    public CartPage checkSummLegalEntity() {
        naturalPersonIcon.shouldBe(visible).click();
        int summNaturalPerson = Integer.parseInt(finalSumm.text().replace(" ", ""));
        legalEntityIcon.shouldBe(visible).click();
        String updatedText = finalSumm
                .shouldNotHave(exactText(String.valueOf(summNaturalPerson)))
                .text()
                .replace(" ", "");
        int summLegalEntity = Integer.parseInt(updatedText);

        assertTrue(summLegalEntity < summNaturalPerson,
                "Сумма для юр. лица (" + summLegalEntity +
                        ") не меньше суммы для физ. лица (" + calculateSummNaturalPerson() + ")");
        return this;
    }

    @Step("Очистить корзину")
    public CartPage clearCart() {
        clearCartButton.shouldBe(visible).click();
        divText("Очистить корзину?").shouldBe(visible, Duration.ofSeconds(10));
        buttonClass("alert_btn btn js-ok").shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public CartPage checkCartIsEmpty() {
        itemsInCart.shouldBe(size(0));
        assertTrue($x("//div[contains(@class, 'catalog__goods__blockwithdots quantity')]/div/span")
                .as("Строка в блоке 'Ваши товары'")
                .text()
                .contains("0 товаров (0 кг)"));
        return this;
    }
}
