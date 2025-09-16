package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ibs.amurova.helpers.AllureScreenshotWatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(AllureScreenshotWatcher.class)
public class BaseTest {

    @BeforeAll
    public static void setupDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;

        Configuration.browserSize = "1920x1080";

        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
    }

    @BeforeEach
    public void openBrowser() {
        Selenide.open("https://ufa.saturn.net/");
    }

    @AfterEach
    public void closeBrowserWindow() {
        Selenide.closeWindow();
    }

    @AfterAll
    public static void closeDriver() {
        Selenide.closeWebDriver();
    }
}
