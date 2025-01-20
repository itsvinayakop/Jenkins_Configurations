import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginAutomationTest {
    public static void main(String[] args) {
        // Set the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        System.out.println("Hey there!");
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
            WebElement myAccountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Automation']"))); // Adjust locator as needed

            // Verify login success

            if (myAccountLink.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

            Thread.sleep(3);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Subscription']")));
            WebElement element= driver.findElement(By.xpath("//h2[text()='Subscription']"));
            scrollToElement(element,driver);




            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='choose']//a")));
            List<WebElement> addCartButton= driver.findElements(By.xpath("//div[@class='choose']//a"));
            System.out.println("Total  Btns:"+ addCartButton.size());

            WebElement addToCart = addCartButton.get(2);
            addToCart.click();
        
            Thread.sleep(5000);

            // LOG OUT FUNCTIONALITY
//            WebElement logOut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Logout']")));
//            logOut.click();
//            System.out.println("User Logged Out");


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


    public static void scrollToElement(WebElement element, WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        Thread.sleep(500);
    }
}
