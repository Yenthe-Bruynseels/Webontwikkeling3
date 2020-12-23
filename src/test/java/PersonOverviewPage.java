import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @Author Yenthe Bruynseels & Bryan Jeetun
 */

public class PersonOverviewPage extends Page {

    public PersonOverviewPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Overview");
    }

    public boolean containsEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
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

    public boolean containsLastName(String userid) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(userid)) {
                found=true;
            }
        }
        return found;
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("p.alert-danger"));
        return (message.equals(errorMsg.getText()));
    }
}