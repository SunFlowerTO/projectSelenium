import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

class SearchPage extends PageBase {

    private By searchBarLocator = By.xpath("//input[@placeholder='Search']");
    private By xMarkLocator = By.xpath("//button[contains(., 'Mind Map')]");


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void doSomeSearch(String keyword) {

        // Form sending with user (search)
        fillInputTextField(searchBarLocator, keyword);

        // close the result slide
        clickElement(xMarkLocator);
    }
}
