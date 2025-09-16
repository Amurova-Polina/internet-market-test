package ibs.amurova.factory;

import com.codeborne.selenide.SelenideElement;

public class ElementsFactory {

    public SelenideElement getElementByType(ElementTypeOption type, ElementAttribute attribute, String attributeValue) {
        switch (type) {
            case DIV:
                return new Div().getElement(attribute, attributeValue);
//            case H2:
//                return new H2().getElement(attribute, attributeValue);
//            case P:
//                return new P().getElement(attribute, attributeValue);
            case A:
                return new Anchor().getElement(attribute, attributeValue);
            default:
                throw new IllegalArgumentException("Неизвестный тип элемента: " + type);
        }
    }
}
