package ibs.amurova.pages.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.page;
import static ibs.amurova.common_elements.CommonElements.aClass;
import static ibs.amurova.common_elements.CommonElements.divText;

public class Header {

    private final SelenideElement cityButton = aClass("header_region_select");


    @Step("Выбрать город")
    public <NextPage> NextPage chooseCity(String cityName, Class<NextPage> nextPageType) {
        cityButton.shouldBe(visible, Duration.ofSeconds(30)).click();
        divText("Выберите город").shouldBe(visible, Duration.ofSeconds(30));
        ElementsCollection cities = $$x("//a[contains(@class, 'region_popup_list_link')]")
                .as("Список городов");
        cities.findBy(text(cityName)).click();
        cityButton.shouldHave(text(cityName));
        return nextPageType.cast(page(nextPageType));
    }
}
