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

public class DeleteContactTest {
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
        driver.get(path); // We don't open our AllContactsUser page with the setUp method because most of the tests require a user to log in. We don't log in our user in the setUp method because we need different users in different tests.
    }

    @After
    public void clean() {
        driver.quit();
    }



    @Test
    public void test_AdminSeesDeleteButtons() {
        submitLogInForm("admin", "t");
        driver.get(path + "?command=AllContactsUser");
        List<WebElement> deleteButtons1 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']"));
        System.out.println(deleteButtons1.size());
        submitAddContactForm("Yenthe", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe@test.be");
        submitAddContactForm("Yenthe2", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe2@test.be");
        submitAddContactForm("Yenthe3", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe3@test.be");
        submitAddContactForm("Yenthe4", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe4@test.be");
        submitAddContactForm("Yenthe5", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe5@test.be");
        List<WebElement> deleteButtons2 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']"));
        System.out.println(deleteButtons2.size());

        assertTrue(deleteButtons1.size() + 5 == deleteButtons2.size()); // shows that there are 5 more delete buttons after the adding of 5 new contacts than before
    }

    @Test
    public void test_AdminDeletesContact() {
        submitLogInForm("admin", "t");
        driver.get(path + "?command=AllContactsUser");
        submitAddContactForm("Yenthe", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe@test.be"); // This is done, just to be sure that there will always be at least 1 contact in the database for if test would be run on it's own multiple times.
        List<WebElement> deleteButtons1 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']")); //puts all delete buttons in a list
        System.out.println(deleteButtons1.size());
        deleteButtons1.get(0).click(); // clicks the first delete button found in the list
        List<WebElement> deleteButtons2 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']")); // puts all remaining delete buttons in a list
        System.out.println(deleteButtons2.size());

        assertTrue(deleteButtons1.size() == deleteButtons2.size() +1 ); // if true, the statement shows that the amount of delete buttons in the second List is 1 less than in the first List, which shows the Admin successfully deleted someone
    }

    @Test
    public void test_OtherUsersDoNotSeeDeleteButtons() {
        submitLogInForm("thibault", "z&g=/=okay");
        driver.findElement(By.id("logOut")); //if user isn't logged in, this line will make the test crash and thus fail.

        driver.get(path + "?command=AllContactsUser");
        List<WebElement> deleteButtons1 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']"));
        System.out.println(deleteButtons1.size());
        submitAddContactForm("Yenthe6", "Bruynseels", "09042020", "0808PM", "+32492606838", "yenthe@test.be");
        List<WebElement> deleteButtons2 = driver.findElements(By.cssSelector("a[href*='Controller?command=DeleteContact&id=']"));
        System.out.println(deleteButtons2.size());

        assertTrue(deleteButtons2.size() == deleteButtons1.size()); // shows the number of delete buttons is the same as before the extra contact has been implemented
        assertTrue(deleteButtons2.size() == 0); // shows that the number of delmete buttons is 0
    }

    @Test
    public void test_NotLoggedInUserDoesNotSeeContactOverview() {
        driver.get(path + "?command=AllContactsUser");
        assertEquals("Home", driver.getTitle());

    }


    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
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

    private void submitLogInForm(String userid, String password) {
        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("logIn"));
        button.click();
    }

}
