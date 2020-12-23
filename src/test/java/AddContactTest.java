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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddContactTest {
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
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setUserid("dummy");
        homePage.setPassword("t");
        homePage.submitLogin();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_FormFilledInCorrectly_AddsContact() {
        String firstname = generateRandomFirstNameInOrderToRunTestMoreThanOnce("Jan");
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName(firstname);
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.containsFirstName(firstname));
    }

    @Test
    public void test_EmptyFirstName_ErrorMessageGivenForFirstNameAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("");
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("No firstname given"));
        assertTrue(contactOverviewPage.hasEmptyFirstName());
        assertTrue(contactOverviewPage.hasStickyLastName("Janssens"));
        assertTrue(contactOverviewPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(contactOverviewPage.hasStickyPhonenumber("+32492606839"));
        assertTrue(contactOverviewPage.hasStickyHour("01:01"));
        assertTrue(contactOverviewPage.hasStickyDate("2020-04-09"));
    }

    @Test
    public void test_EmptyLastName_ErrorMessageGivenForLastNameAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("Jan");
        contactOverviewPage.setLastName("");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("No last name given"));
        assertTrue(contactOverviewPage.hasStickyFirstName("Jan"));
        assertTrue(contactOverviewPage.hasEmptyLastName());
        assertTrue(contactOverviewPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(contactOverviewPage.hasStickyPhonenumber("+32492606839"));
        assertTrue(contactOverviewPage.hasStickyHour("01:01"));
        assertTrue(contactOverviewPage.hasStickyDate("2020-04-09"));
    }

    @Test
    public void test_EmptyEmail_ErrorMessageGivenForEmailAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("Jan");
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("No email given"));
        assertTrue(contactOverviewPage.hasStickyFirstName("Jan"));
        assertTrue(contactOverviewPage.hasStickyLastName("Janssens"));
        assertTrue(contactOverviewPage.hasEmptyEmail());
        assertTrue(contactOverviewPage.hasStickyPhonenumber("+32492606839"));
        assertTrue(contactOverviewPage.hasStickyHour("01:01"));
        assertTrue(contactOverviewPage.hasStickyDate("2020-04-09"));
    }

    @Test
    public void test_EmptyPhonenumber_ErrorMessageGivenForPhonenumberAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("Jan");
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("Phone number can't be empty."));
        assertTrue(contactOverviewPage.hasStickyFirstName("Jan"));
        assertTrue(contactOverviewPage.hasStickyLastName("Janssens"));
        assertTrue(contactOverviewPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(contactOverviewPage.hasEmptyPhonenumber());
        assertTrue(contactOverviewPage.hasStickyHour("01:01"));
        assertTrue(contactOverviewPage.hasStickyDate("2020-04-09"));
    }

    @Test
    public void test_EmptyHour_ErrorMessageGivenForHourAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("Jan");
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("");
        contactOverviewPage.setDate("09042020");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("date or hour invalid"));
        assertTrue(contactOverviewPage.hasStickyFirstName("Jan"));
        assertTrue(contactOverviewPage.hasStickyLastName("Janssens"));
        assertTrue(contactOverviewPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(contactOverviewPage.hasStickyPhonenumber("+32492606839"));
        assertTrue(contactOverviewPage.hasEmptyHour());
        assertTrue(contactOverviewPage.hasStickyDate("2020-04-09"));
    }

    @Test
    public void test_EmptyDate_ErrorMessageGivenForDateAndOtherValuesKept() {
        ContactOverviewPage contactOverviewPage = PageFactory.initElements(driver, ContactOverviewPage.class);
        contactOverviewPage.setFirstName("Jan");
        contactOverviewPage.setLastName("Janssens");
        contactOverviewPage.setEmail("jan.janssens@hotmail.com");
        contactOverviewPage.setPhonenumber("+32492606839");
        contactOverviewPage.setHour("0101PM");
        contactOverviewPage.setDate("");
        contactOverviewPage.submitAddContact();

        assertTrue(contactOverviewPage.hasErrorMessage("date or hour invalid"));
        assertTrue(contactOverviewPage.hasStickyFirstName("Jan"));
        assertTrue(contactOverviewPage.hasStickyLastName("Janssens"));
        assertTrue(contactOverviewPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(contactOverviewPage.hasStickyPhonenumber("+32492606839"));
        assertTrue(contactOverviewPage.hasStickyHour("01:01"));
        assertTrue(contactOverviewPage.hasEmptyDate());
    }

    private String generateRandomFirstNameInOrderToRunTestMoreThanOnce(String component) {
        int random = (int) (Math.random() * 1000 + 1);
        return random + component;
    }
}