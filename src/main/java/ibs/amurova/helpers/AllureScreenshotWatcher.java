    package ibs.amurova.helpers;

    import com.codeborne.selenide.Selenide;
    import io.qameta.allure.Allure;
    import lombok.SneakyThrows;
    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.extension.ExtensionContext;
    import org.junit.jupiter.api.extension.TestWatcher;
    import org.openqa.selenium.OutputType;

    import java.io.ByteArrayInputStream;

    public class AllureScreenshotWatcher implements TestWatcher {

        @SneakyThrows
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            byte[] screenshotBytes = Selenide.screenshot(OutputType.BYTES);
            Assertions.assertNotNull(screenshotBytes, "Не удалось сделать скриншот!");
            Allure.addAttachment("Скриншот перед падением", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
        }
    }
