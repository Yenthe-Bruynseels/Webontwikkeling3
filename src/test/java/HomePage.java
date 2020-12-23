import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(id="userid")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="logIn")
    private WebElement signUpButton;

    @FindBy(id = "logOut")
    private WebElement logOutButton;

    public HomePage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Home");
    }

    public void setUserid(String userid) {
        usernameField.clear();
        usernameField.sendKeys(userid);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        signUpButton.click();
    }

    public void submitLogout() {
        logOutButton.click();
    }

    public boolean hasWelcomeMessage (String message) {
        WebElement welcomeMsg = driver.findElement(By.id("welcomeMessage"));
        return message.equals(welcomeMsg.getText());
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean logOutButtonIsPresent(){
        try {
            WebElement webElement = driver.findElement(By.id("logOut"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean ContactsNavigateButtonIsPresent(){
        try {
            WebElement webElement = driver.findElement(By.linkText("Contacts"));
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }
}
