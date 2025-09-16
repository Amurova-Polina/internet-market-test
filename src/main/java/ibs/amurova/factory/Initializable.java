package ibs.amurova.factory;

import com.codeborne.selenide.SelenideElement;

public interface Initializable {
    SelenideElement getElement(ElementAttribute attribute, String attributeValue);
}
