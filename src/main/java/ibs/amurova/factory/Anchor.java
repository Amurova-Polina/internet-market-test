package ibs.amurova.factory;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Anchor implements Initializable {

    @Override
    public SelenideElement getElement(ElementAttribute attribute, String attributeValue) {
        return switch (attribute) {
            case TEXT -> $x("//a[text()='" + attributeValue + "']");
            case ID -> $x("//a[contains(@id, '" + attributeValue + "')]");
            case CLASS -> $x("//a[contains(@class, '" + attributeValue + "')]");
        };
    }
}
