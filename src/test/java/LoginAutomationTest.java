import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginAutomationTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        // Remove headless mode for testing
        // chromeOptions.addArguments("--headless");

        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            driver.get("https://www.automationexercise.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increase timeout

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            emailField.sendKeys("tester@gmail.com");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
            passwordField.sendKeys("tester");

            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-qa='login-button']")));
            signInButton.click();

            // Log message to help debug
            System.out.println("Waiting for the account link to be visible...");

            WebElement myAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account"))); // Or use XPath if needed

            if (myAccountLink.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
                System.out.println("Hello Vinayak");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
