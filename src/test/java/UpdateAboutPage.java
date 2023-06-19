import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdateAboutPage extends PageBase {

    private By aboutUsLocator = By.xpath("//span[contains(text(),'About us')]");

    public UpdateAboutPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://miro.com");
    }

    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitAndReturnElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAboutUsDisplayed() {
        return isElementDisplayed(aboutUsLocator);
    }

    public boolean isOnAboutPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://miro.com/about/");
    }
}

