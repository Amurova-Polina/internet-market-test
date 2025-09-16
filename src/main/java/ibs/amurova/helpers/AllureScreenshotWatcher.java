package ibs.amurova.helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AllureScreenshotWatcher implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("привет меня вызвали");

        byte[] screenshotBytes = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
    }
}
