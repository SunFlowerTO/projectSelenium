import org.junit.*;
import org.openqa.selenium.*;
import org.junit.Assert;
import org.junit.Test;

class SignoutPage extends PageBase {

    private By profileImageLocator = By.xpath("//a[@class='more-user-action-toggle']/img[@class='profile-picture']");
    private By logoutButtonLocator = By.xpath("//input[@id='logoutSubmitBtn' and @type='submit']");
    private By logoutCheckerLocatoe = By.xpath("//div[@id='linkedinContainer']//h1");

    public SignoutPage(WebDriver driver) {
        super(driver);
    }

    public void DoSignout() {

        clickElement(profileImageLocator);
        clickElement(logoutButtonLocator);
    }

    void checkSignout() {
        driver.get("https://www.miro.com/login");

        WebElement loginForm = driver.findElement(By.cssSelector("form[id='login-form']"));
        Assert.assertNotNull("Login page is not displayed", loginForm);
    }

}
