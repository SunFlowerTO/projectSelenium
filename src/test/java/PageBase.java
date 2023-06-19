import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public void clickElement(By locator) {
        WebElement element = waitAndReturnElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void fillInputTextField(By locator, String value) {
        WebElement element = waitAndReturnElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void myWait(int t) {
        this.driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
    }
}
