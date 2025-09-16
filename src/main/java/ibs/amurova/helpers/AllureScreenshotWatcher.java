package ibs.amurova.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.FileInputStream;

public class AllureScreenshotWatcher implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("привет меня вызвали");

        String screenshotPath = Selenide.screenshot("failed");
        assert screenshotPath != null;
        File screenshotFile = new File(screenshotPath);

        try (FileInputStream fis = new FileInputStream(screenshotFile)) {
            Allure.addAttachment("Screenshot on Failure", "image/png", fis, ".png");
        }
    }
}
