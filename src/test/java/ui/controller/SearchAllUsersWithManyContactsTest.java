package ui.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchAllUsersWithManyContactsTest {

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
        driver.get(path);

    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_ContactsToWarnIsVisible() { //Versimpelde test, aangezien dit totaal irrelevant is voor mijn eigen story, maar dit wel aanwezig moet zijn door de implementatie van story08. Vandaar dat er getest wordt of de boodschap boven de tabel er effectief is.
        submitLogInForm("admin", "t");
        driver.get(path + "?command=SearchContactsSinceLastPositiveTest");
        WebElement h2 = driver.findElement(By.id("SearchContactsToWarn"));
        String h2text = h2.getText();
        assertEquals("Contact the following people", h2text );
    }

    @Test
    public void test_NoContactsToWarnMessageIsVisible() { //Versimpelde test, aangezien dit totaal irrelevant is voor mijn eigen story, maar dit wel aanwezig moet zijn door de implementatie van story08. Vandaar dat er getest wordt of de boodschap boven de tabel er effectief is.
        submitLogInForm("yenthe", "yeet");
        driver.get(path + "?command=SearchContactsSinceLastPositiveTest");
        WebElement h2 = driver.findElement(By.id("SearchContactsToWarn0"));
        String h2text = h2.getText();
        assertEquals("Goed gedaan, er moeten geen mensen gecontacteerd worden.", h2text );
    }

    @Test
    public void test_OverviewOfPeopleWith25orMoreContactsIsShown() {
        submitLogInForm("admin", "t");
        driver.get(path + "?command=SearchContactsSinceLastPositiveTest");
        WebElement h2 = driver.findElement(By.id("SearchMany"));
        String h2text = h2.getText();
        assertEquals("The following people have 25 or more contacts", h2text );

        WebElement table = driver.findElement(By.id("ManyContactsTable"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        driver.get(path);
        driver.findElement(By.id("logOut")).click();
        submitLogInForm("24contacts", "t");
        driver.get(path + "?command=AllContactsUser");
        submitAddContactForm("Yenthe", "Bruynseels", "09042022", "0808PM", "+32492606838", "yenthe@test.be");
        driver.get(path);
        driver.findElement(By.id("logOut")).click();
        submitLogInForm("admin", "t");
        driver.get(path + "?command=SearchContactsSinceLastPositiveTest");

        WebElement table1 = driver.findElement(By.id("ManyContactsTable"));
        List<WebElement> rows2 = table1.findElements(By.tagName("tr"));
        assertTrue(rows.size() + 1 == rows2.size()); //24Contacts now has 25 so shows in the list as well, thus 1 more tr tag in the table.


        driver.get(path + "?command=AllContactsUser");
        driver.findElement(By.id("AllContactsButton")).click();
        List<WebElement> deleteButtons1 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']"));
        deleteButtons1.get(deleteButtons1.size()-1).click();
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitLogInForm(String userid, String password) {
        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("logIn"));
        button.click();
    }

    private void submitAddContactForm(String firstName, String lastName, String dateKeys, String hourKeys, String phonenumber, String email) {
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        WebElement dateBox =  driver.findElement(By.id("date"));
        dateBox.sendKeys(dateKeys);
        WebElement hourBox = driver.findElement(By.id("hour"));
        hourBox.sendKeys(hourKeys);
        fillOutField("phonenumber", phonenumber);
        fillOutField("email", email);


        WebElement button = driver.findElement(By.id("addContact"));
        button.click();
    }
}
