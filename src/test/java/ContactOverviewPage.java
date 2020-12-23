import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ContactOverviewPage extends Page{
    @FindBy(id = "firstName") private WebElement firstNameField;
    @FindBy(id = "lastName") private WebElement lastNameField;
    @FindBy(id = "date") private WebElement dateField;
    @FindBy(id = "hour") private WebElement hourField;
    @FindBy(id = "phonenumber") private WebElement phonenumberField;
    @FindBy(id = "email") private WebElement emailField;
    @FindBy(id = "addContact") private WebElement addContactButton;

    public ContactOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=AllContactsUser");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void setHour(String hour) {
        hourField.clear();
        hourField.sendKeys(hour);
    }

    public void setPhonenumber(String phonenumber) {
        phonenumberField.clear();
        phonenumberField.sendKeys(phonenumber);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void submitAddContact() {
        addContactButton.click();
    }

    public boolean hasErrorMessageNotAuthorized (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }

    public int countContacts(){
        List<WebElement> contacts = driver.findElements(By.tagName("tr"));
        return contacts.size()-1;
    }

    public boolean containsFirstName(String firstName) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(firstName)) {
                found=true;
            }
        }
        return found;
    }

    public boolean hasStickyLastName (String lastname) {
        return lastname.equals(lastNameField.getAttribute("value"));
    }

    public boolean hasStickyFirstName (String firstname) {
        return firstname.equals(firstNameField.getAttribute("value"));
    }

    public boolean hasStickyDate (String date) {
        return date.equals(dateField.getAttribute("value"));
    }

    public boolean hasStickyHour (String hour) {
        return hour.equals(hourField.getAttribute("value"));
    }

    public boolean hasStickyEmail (String email) {
        return email.equals(emailField.getAttribute("value"));
    }

    public boolean hasStickyPhonenumber (String phonenumber) {
        return phonenumber.equals(phonenumberField.getAttribute("value"));
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

    public boolean hasEmptyPhonenumber() {
        return phonenumberField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyDate() {
        return dateField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyHour() {
        return hourField.getAttribute("value").isEmpty();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}
