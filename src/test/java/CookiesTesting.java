import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookiesTesting extends PageBase {
    public CookiesTesting(WebDriver driver) {
        super(driver);
    }

    public static boolean hasCookies(WebDriver driver) {
        return !driver.manage().getCookies().isEmpty();
    }

    public static void testCookies(WebDriver driver) {
        boolean hasCookies = hasCookies(driver);
        assert hasCookies : "No cookies found";
        
        System.out.println("All Cookies:");
        for (Cookie cookie : driver.manage().getCookies()) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            System.out.println("Name: " + cookieName);
            System.out.println("Value: " + cookieValue);
            System.out.println("---------------------------------");
        }
    }
}
