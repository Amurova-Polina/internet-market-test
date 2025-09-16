package ibs.amurova.pages;

import com.codeborne.selenide.SelenideElement;
import ibs.amurova.pages.abstraction.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static ibs.amurova.common_elements.CommonElements.divClass;
import static ibs.amurova.common_elements.CommonElements.divText;

public class MainPage extends BasePage {
    private final SelenideElement acceptCookieButton = divText("Принять").as("Кнопка 'Принять' в окне кук");
    private final SelenideElement closeModalButton = divClass("system_popup_hide system_popup_hide_new js-close-modal-application");

    @Step("Закрыть окно кук и рекламные модалки, если есть")
    public MainPage acceptCookiesAndCloseModals() {
        if (acceptCookieButton.is(visible)) {
            acceptCookieButton.click();
        }
        if (divClass("application_popup system_popup system_popup_new").is(visible)) {
            closeModalButton.click();
        }
        return this;
    }
}
