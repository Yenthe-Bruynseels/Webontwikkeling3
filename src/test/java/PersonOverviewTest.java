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

/**
 * @Author Yenthe Bruynseels & Bryan Jeetun
 */


public class PersonOverviewTest {
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
    public void test_PersonOverview_NotLoggedIn_ShowsError() {
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.hasErrorMessage("Je hebt niet de juiste rechten om deze pagina te bezoeken."));
    }

    @Test
    public void test_PersonOverview_LoggedInAsNormalUser_ShowsError() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("Thibault");
        homePage.setPassword("z&g=/=okay");
        homePage.submitLogin();
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertEquals("Home", personOverviewPage.getTitle());
        assertTrue(personOverviewPage.hasErrorMessage("Je hebt niet de juiste rechten om deze pagina te bezoeken."));
    }

    @Test
    public void test_PersonOverview_loggedInAsAdmin_ShowsOverview() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLogin();
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertTrue(personOverviewPage.containsEmail("admin@ucll.be"));
        assertTrue(personOverviewPage.containsFirstName("Admini"));
        assertTrue(personOverviewPage.containsLastName("Strator"));
    }

}