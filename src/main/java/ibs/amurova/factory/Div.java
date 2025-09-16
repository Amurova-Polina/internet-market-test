package ibs.amurova.factory;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Div implements Initializable {

    @Override
    public SelenideElement getElement(ElementAttribute attribute, String attributeValue) {
        return switch (attribute) {
            case TEXT -> $x("//div[text()='" + attributeValue + "']");
            case ID -> $x("//div[contains(@id, '" + attributeValue + "')]");
            case CLASS -> $x("//div[contains(@class, '" + attributeValue + "')]");
        };
    }
}
