import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends Page {


    @FindBy(id="userid")
    private WebElement usernameField;

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signUp")
    private WebElement signUpButton;

    public RegisterPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Register");
    }

    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public HomePage submitValid() {
        signUpButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public void submitInvalid() {
        signUpButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyUsername(String userid) {return userid.equals(usernameField.getAttribute("value"));}

    public boolean hasStickyFirstName(String firstname) {
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasStickyPassword (String password) {
        return password.equals(passwordField.getAttribute("value"));
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName() {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail() {
        return emailField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPassword() {
        return passwordField.getAttribute("value").isEmpty();

    }
}
