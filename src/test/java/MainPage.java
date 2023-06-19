import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


class MainPage extends PageBase {
    

    private By footerBy = By.xpath("//div[contains(@class, 'FooterLinks__Copyright-sc-1rsn5ej-1')]");
    private By hoverBy = By.xpath("//a[@aria-label='Miro']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getFooterText() {
        return waitAndReturnElement(footerBy).getText();
    }
    public  MainPage doHover() {
        hoverOverElement(hoverBy);
        return this;
    }

    public void hoverOverElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
}

    public String getHoverText() {
    WebElement hoverElement = driver.findElement(By.xpath("//div[@class='logo__Miro']"));
    return hoverElement.getText();
}

}
