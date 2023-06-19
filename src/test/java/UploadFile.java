import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UploadFile extends PageBase {

    private By importButtonLocator = By.xpath("//div[@aria-label='Import']");
    private By fileUploadLocator = By.xpath("//input[@type='file' and @name='file']");
    private By uploadFromBackupLocator = By.xpath("//a[contains(text(),'Upload from Backup')]");

    public UploadFile(WebDriver driver) {
        super(driver);
    }

    public void makeInputElementVisible(String id) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('" + id + "').style.display='block';");
    }

    public void uploadFile(String path) {
        clickElement(importButtonLocator);
        makeInputElementVisible("file");
        WebElement fileUploadElement = waitAndReturnElement(fileUploadLocator);
        fileUploadElement.sendKeys(path);
        clickUploadFromBackup();
    }

    public void clickUploadFromBackup() {
        clickElement(uploadFromBackupLocator);
    }
}
