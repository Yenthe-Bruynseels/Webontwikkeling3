import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogoutTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Software TI\\SeleniumShit\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_logout_works_when_you_are_logged_in(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        // eerst inloggen
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLogin();

        // kijk of je de logout button kan zien
        assertTrue(homePage.logOutButtonIsPresent());
        // uitloggen
        homePage.submitLogout();
        // kijk of je bent uitgelogd en de logout button dus weg is
        assertFalse(homePage.logOutButtonIsPresent());

    }

    @Test
    public void test_can_not_logout_when_not_logged_in() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertFalse(homePage.logOutButtonIsPresent());
    }

}
