import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;


class SigninPage extends PageBase {

    private By loginPageButtonLocator = By.xpath("//span[contains(text(), 'Login')]");
    private By emailInputLocator = By.xpath("//input[@name='email']");
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By loginButtonLocator = By.xpath("//button[@data-testid='mr-form-login-btn-signin-1']");

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    // Fill simple form and send (Login)
    public void DoSignin(String id, String password) {
        clickElement(loginPageButtonLocator);
        fillInputTextField(emailInputLocator, id);
        fillInputTextField(passwordInputLocator, password);
        clickElement(loginButtonLocator);
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

    void CheckSignIn() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://miro.com/app/dashboard/";
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
