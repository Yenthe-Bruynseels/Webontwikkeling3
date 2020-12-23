import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactOverviewTest {
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
    public void test_Not_Logged_In_User_Cannot_Reach_Contact_Overview()  {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals("Home", contactOverviewPage.getTitle());
        assertTrue(contactOverviewPage.hasErrorMessageNotAuthorized("Je hebt niet de juiste rechten om deze pagina te bezoeken."));
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Home_To_Contacts() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("Thibault");
        homePage.setPassword("z&g=/=okay");
        homePage.submitLogin();
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals("Contacts", contactOverviewPage.getTitle());
    }

    @Test
    public void test_User_Without_Contacts_Shows_No_Contacts() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("nocontacts");
        homePage.setPassword("t");
        homePage.submitLogin();
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals("Contacts", contactOverviewPage.getTitle());
        assertEquals(contactOverviewPage.countContacts(),0);
    }

    @Test
    public void test_User_With_Contacts_Shows_Contacts()  {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("Thibault");
        homePage.setPassword("z&g=/=okay");
        homePage.submitLogin();
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        assertEquals("Contacts", contactOverviewPage.getTitle());
        int current = contactOverviewPage.countContacts();

        contactOverviewPage.setFirstName("testie");
        contactOverviewPage.setLastName("tester");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.setHour("0808PM");
        contactOverviewPage.setEmail("yenthe@test.be");
        contactOverviewPage.submitAddContact();
        int newNumber = contactOverviewPage.countContacts();
        assertEquals(newNumber -1, current);
    }
}
