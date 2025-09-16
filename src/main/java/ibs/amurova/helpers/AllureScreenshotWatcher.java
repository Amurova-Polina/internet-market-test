package ibs.amurova.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class AllureScreenshotWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
        assert screenshot != null;
        System.out.println("привет меня вызвали");
        Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshot), ".png");
    }
}
