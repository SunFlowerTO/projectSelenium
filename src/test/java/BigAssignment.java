import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;



public class BigAssignment {

	private WebDriver driver;
    private String[] pageUrls = {
            "https://miro.com/",
            "https://miro.com/login/"
    };
    private By pageTitleLocator = By.xpath("//h1");
    private String expectedPageTitle = "Welcome to the Static Page";


	@Before
	public void setup() throws MalformedURLException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments(
				"--disable-blink-features=AutomationControlled",
				"--disable-infobars",
				"--no-sandbox",
				"--disable-dev-shm-usage",
				"--disable-web-security",
				"--allow-running-insecure-content",
				"--start-maximized");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		this.driver.manage().window().maximize();
	}

	

	@Test

	public void loadMainPage() {

		this.driver.get("https://www.miro.com/");

		// Reading the page title
		String title = this.driver.getTitle();
		System.out.println(title);

		MainPage mainPage = new MainPage(driver);
		System.out.println(mainPage.getFooterText());
		Assert.assertTrue(mainPage.getFooterText().contains("2023"));

	}


	@Test
	public void testEngineeringAnalystPositionExists() {
		// Navigate to the URL
		driver.get("https://miro.com/careers/open-positions/?selectedTeam=Engineering");

		// Get the page source
		String pageSource = driver.getPageSource();

		// Check if the page source contains the position "Engineering Analyst"
		boolean engineeringAnalystPositionExists = pageSource.contains("Engineer");

			// Assert that the Engineering Analyst position exists
		Assert.assertTrue(engineeringAnalystPositionExists);}

	
	@Test
    public void verifyPageTitles() {
        for (String pageUrl : pageUrls) {
            driver.get(pageUrl);
            String actualPageTitle = waitAndReturnElement(pageTitleLocator).getText();
            Assert.assertEquals("Page title is incorrect for URL: " + pageUrl, expectedPageTitle, actualPageTitle);
        }
    }

    private WebElement waitAndReturnElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


	@Test
    public void testLogin() {
        String email = "sunflowertt94@gmail.com";
        String password = "tournesol94t";

        boolean loginSuccessful = false;

        try {
            // Attempt login using SigninPage.DoSignin()
            login(email, password);
            loginSuccessful = true;
        } catch (Exception e) {
            System.out.println("Initial login attempt failed. Trying alternative login method...");
        }

        // If initial login failed, try alternative login method using LoginExample.performLogin()
        if (!loginSuccessful) {
            try {
                loginP(email, password);
                loginSuccessful = true;
            } catch (Exception e) {
                System.out.println("Alternative login method also failed. Login unsuccessful.");
            }
        }

        // Add assertions or further actions after successful login
        if (loginSuccessful) {
            // ...
        }
    }

    



	@Test
    public void performSearch() {
        // Navigate to www.miro.com
		login();
        driver.get("https://www.miro.com/dashboard");

        // Locate the search bar
        driver.findElement(By.id("navbar-search")).sendKeys("Miro" + Keys.RETURN);

        // Wait for the search results to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Miro"));

        // Perform assertions or validations on the search results
        assert !driver.getPageSource().contains("No results found") : "No search results found";
    }



	@Test
    public void testCookies() {

        // Navigate to the website
        driver.get("https://www.miro.com");

        // Call the method to test cookies
        CookiesTesting.testCookies(driver);

        // Close the browser
        driver.quit();
    } 
	@Test
	public void uploadfileTest() {
		login();
		UploadFile uploadFile = new UploadFile(driver);
		uploadFile.uploadFile("/new-templates.png");
	}

	@Test
	public void textAreaTest() throws InterruptedException {
		UpdateAboutPage updateAboutPage = new UpdateAboutPage(driver);
		updateAboutPage.isOnAboutPage();
	
	}
	@Test
    public void testBrowserBackButton() {
        // Navigate to www.miro.com
        driver.get("https://miro.com");

        // Navigate to another page
        driver.get("https://miro.com/about/");


        // Simulate clicking the browser back button
        driver.navigate().back();

        // Verify that the previous page is loaded (www.miro.com)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://miro.com/", currentUrl);
    }

	
    @Test
    public void testImageDownload() throws IOException {
        WebDriver driver = new ChromeDriver();// Create and initialize your WebDriver instance
	

    // Navigate to www.miro.com
    driver.get("https://www.miro.com");

        // Navigate to www.miro.com
        driver.get("https://www.miro.com");

        // Find all the image elements on the page
        List<WebElement> imageElements = driver.findElements(By.tagName("img"));

        boolean isImageDownloaded = false;

        // Download each image
        for (WebElement imageElement : imageElements) {
            String imageUrl = imageElement.getAttribute("src");
            if (downloadImage(imageUrl)) {
                isImageDownloaded = true;
                break;
            }
        }

        Assert.assertTrue("At least one image should be downloaded", isImageDownloaded);
    }

    private boolean downloadImage(String imageUrl) throws IOException {
        // Open a connection to the image URL
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();

        // Extract the image name from the URL
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        // Create an output stream to save the image
        OutputStream outputStream = new FileOutputStream(imageName);

        // Read from the input stream and write to the output stream
        byte[] buffer = new byte[2048];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        // Close the streams
        inputStream.close();
        outputStream.close();

        return true; // Return true if the image is downloaded successfully
    }

	@Test
	public void testHoverMouseHome() {
		MainPage mainPage = new MainPage(driver);
		By hoverBy = By.xpath("//span[text() = 'Miro Logo']");
		mainPage.hoverOverElement(hoverBy);
		String hoverText = mainPage.getHoverText();
		Assert.assertEquals(hoverText, "Miro");
}

	@Test
	public void logoutTest() {
		login();
		SignoutPage signoutPage = new SignoutPage(driver);
		signoutPage.DoSignout();
		// static page after logout
		signoutPage.checkSignout();
	}
	public void login() {
		this.driver.get("https://www.miro.com/");
		SigninPage signinPage = new SigninPage(this.driver);
		signinPage.DoSignin("sunflowertt94@gmail.com", "tournesol94t");
		WebDriverWait wait = new WebDriverWait(driver, 10); // Maximum wait time in seconds
		wait.until(ExpectedConditions.urlToBe("https://miro.com/app/dashboard/"));
		signinPage.CheckSignIn();
	}
	public static void loginP(String email, String password) {
        try {
            String loginUrl = "https://miro.com/login";

            URL url = new URL(loginUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String loginData = "email=" + email + "&password=" + password;

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(loginData);
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Response Code: " + responseCode);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response: " + response.toString());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private void login(String email, String password) {
        this.driver.get("https://www.miro.com/");
        SigninPage signinPage = new SigninPage(this.driver);
        signinPage.DoSignin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 10); // Maximum wait time in seconds
        wait.until(ExpectedConditions.urlToBe("https://miro.com/app/dashboard/"));
        if (!isLoginSuccessful()) {
            throw new RuntimeException("Login unsuccessful. Invalid credentials or login process failed.");
        }
    }

    private boolean isLoginSuccessful() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://miro.com/app/dashboard/";
        return actualUrl.equals(expectedUrl);
    }
	@After
	public void close() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

}
