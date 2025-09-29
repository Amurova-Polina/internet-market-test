package ibs.amurova.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ibs.amurova.pages.base.BasePage;
import ibs.amurova.pages.common.TopMenu;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ibs.amurova.common_elements.CommonElements.divClass;
import static ibs.amurova.common_elements.CommonElements.h1Class;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPage extends BasePage {
    private final TopMenu topMenu = new TopMenu();

    private final SelenideElement cartIcon = topMenu.getCartIcon();
    private final SelenideElement cartRedCounterIcon = topMenu.getCartRedCounterIcon();
    private final SelenideElement productTitle = h1Class("catalog__goods__header__title")
            .as("Название товара");
    private final SelenideElement inCartButton = $x("//button[contains(@class, 'new_red_btn js-addCart-btn')]//span[contains(text(), 'В корзину')]")
            .as("Кнопка 'В корзину' или 'В корзине'");
    private final SelenideElement productArticul = divClass("catalog__goods__header__articul")
            .as("Артикул товара");

    private final ElementsCollection colorOptions = $$x("//a[contains(@class, 'catalog__goods__info__size__list__item')]")
            .as("Варианты цветов товара");


    @Step("Проверить, что открылась карточка товара с заголовком \"{title}\" и отображается корректно")
    public ProductPage checkProductPageOpen(String title) {
        productTitle.shouldBe(visible, Duration.ofSeconds(30)).shouldHave(text(title));
        return this;
    }

    @Step("Проверить, что в карточке товара отображается правильный артикул")
    public ProductPage checkArticul(String articul) {
        assertTrue(productArticul.has(text(articul)),
                "Артикул в карточке товара неверный"
        );
        return this;
    }

    @Step("Выбрать цвет товара \"{color}\"")
    public ProductPage setColor(String color) {
        colorOptions.filter(visible).shouldBe(sizeGreaterThan(0));
        colorOptions
                .asFixedIterable()
                .stream()
                .filter(x -> x.has(attribute("title", color)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Цвет не найден или недоступен"))
                .click();
        assertTrue(
                $x("//span[contains(@class, 'catalog__goods__info__size__list__item active')]")
                        .as("Плитка выбранного цвета товара")
                        .has(attribute("title", color))
                , "Нужный цвет не установлен"
        );
        return this;
    }

    @Step("Положить товар в корзину")
    public ProductPage clickInCartButton() {
        inCartButton.click();
        return this;
    }

    @Step("Проверить, что на иконке корзины появилось обозначение кол-ва товаров \"{amount}\"")
    public ProductPage checkRedCounterIcon(String amount) {
        cartRedCounterIcon.shouldNotBe(hidden);
        cartRedCounterIcon.shouldHave(
                text(amount).because("На иконке корзины не появилось обозначение кол-ва товаров '" + amount + "'")
        );
        return this;
    }
}
