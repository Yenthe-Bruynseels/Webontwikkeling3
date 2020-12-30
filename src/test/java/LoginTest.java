import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import ui.controller.Home;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest{
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
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
    public void test_LogIn_AllFieldsFilledInCorrectly_UserIsLoggedIn() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasWelcomeMessage("Welcome, Admini"));
    }

    @Test
    public void test_LogIn_UserIdNotFilledIn_ErrorMesssageGiven() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("");
        homePage.setPassword("t");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid username/password"));
    }

    @Test
    public void test_LogIn_PasswordNotFilledIn_ErrorMessageGiven() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No password given"));
    }

    @Test
    public void test_LogIn_UserIdFilledIn_PasswordFilledIn_WrongCombination_ErrorMessageGiven() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("a");
        homePage.submitLogin();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid username/password"));
    }

}
