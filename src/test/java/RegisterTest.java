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

public class RegisterTest {

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
    public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
        // GIVEN STEP = context
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername(generateRandomUseridInOrderToRunTestMoreThanOnce("jakke"));
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("1234");

        // WHEN STEP = action
        HomePage homePage = registerPage.submitValid();

        // THEN STEP = result
        assertEquals("Home", homePage.getTitle());
        logInAsAdmin(homePage);
        PersonOverviewPage personOverviewPage = PageFactory.initElements(driver, PersonOverviewPage.class);
        assertTrue(personOverviewPage.containsEmail("jan.janssens@hotmail.com"));
    }

    @Test
    public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername("jakke");
        registerPage.setFirstName("");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("1234");

        registerPage.submitInvalid();

        assertEquals("Sign Up", registerPage.getTitle());
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
        assertTrue(registerPage.hasStickyUsername("jakke"));
        assertTrue(registerPage.hasEmptyFirstName());
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("1234"));
    }



	@Test
	public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername("jakke");
        registerPage.setFirstName("Jan");
        registerPage.setLastName("");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("1234");

        registerPage.submitInvalid();

        assertEquals("Sign Up", registerPage.getTitle());
        assertTrue(registerPage.hasErrorMessage("No last name given"));
        assertTrue(registerPage.hasStickyUsername("jakke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasEmptyLastName());
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasStickyPassword("1234"));
    }

	@Test
	public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername("jakke");
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("");
        registerPage.setPassword("1234");

        registerPage.submitInvalid();

        assertEquals("Sign Up", registerPage.getTitle());
        assertTrue(registerPage.hasErrorMessage("No email given"));
        assertTrue(registerPage.hasStickyUsername("jakke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasEmptyEmail());
        assertTrue(registerPage.hasStickyPassword("1234"));
	}

	@Test
	public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername("jakke");
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("");

        registerPage.submitInvalid();

        assertEquals("Sign Up", registerPage.getTitle());
        assertTrue(registerPage.hasErrorMessage("No password given"));
        assertTrue(registerPage.hasStickyUsername("jakke"));
        assertTrue(registerPage.hasStickyFirstName("Jan"));
        assertTrue(registerPage.hasStickyLastName("Janssens"));
        assertTrue(registerPage.hasStickyEmail("jan.janssens@hotmail.com"));
        assertTrue(registerPage.hasEmptyPassword());
	}
	
	@Test
	public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
        String userid = generateRandomUseridInOrderToRunTestMoreThanOnce("jakke");
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setUsername(userid);
        registerPage.setFirstName("Jan");
        registerPage.setLastName("Janssens");
        registerPage.setEmail("jan.janssens@hotmail.com");
        registerPage.setPassword("1234");

        HomePage homePage = registerPage.submitValid();
        assertEquals("Home", homePage.getTitle());

        RegisterPage registerPage1 = PageFactory.initElements(driver, RegisterPage.class);
        registerPage1.setUsername(userid);
        registerPage1.setFirstName("Jan");
        registerPage1.setLastName("Janssens");
        registerPage1.setEmail("jan.janssens@hotmail.com");
        registerPage1.setPassword("1234");

        registerPage1.submitInvalid();

        assertEquals("Sign Up", registerPage1.getTitle());
        assertTrue(registerPage1.hasErrorMessage("Deze user bestaat al."));

	}


    private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
        int random = (int) (Math.random() * 1000 + 1);
        return random + component;
    }

    private void logInAsAdmin(HomePage homePage) {
        homePage.setUserid("admin");
        homePage.setPassword("t");
        homePage.submitLogin();
    }
}