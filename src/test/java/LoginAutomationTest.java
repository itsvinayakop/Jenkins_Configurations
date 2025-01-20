import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginAutomationTest {
    public static void main(String[] args) {
        // Set the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Configure ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        //String userDataDir = "/tmp/selenium_user_data_" + System.getenv("BUILD_ID");
        //chromeOptions.addArguments("user-data-dir=" + userDataDir);


        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        //chromeOptions.addArguments("--headless"); //Enable for Jenkins

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            // Navigate to the login page
            driver.get("https://www.automationexercise.com/login");

            // Set an explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Locate the email field and enter the email address
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            emailField.sendKeys("tester@gmail.com");

            // Locate the password field and enter the password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
            passwordField.sendKeys("tester");

            // Locate and click the login button
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-qa='login-button']")));
            signInButton.click();

            // Wait for the account link to appear
            WebElement myAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account"))); // Adjust locator as needed

            // Verify login success
            if (myAccountLink.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Quit the WebDriver
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
