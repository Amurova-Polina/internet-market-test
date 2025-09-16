package ibs.amurova.helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class AllureScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }
    }

    public static void setupAllureSelenide() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }
}
