package xsearchamazon;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        System.out.println("Start Tests: TestCases");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "logs" + File.separator + "chromedriver.log");

        driver = new ChromeDriver(options);

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void logStatus(String testCaseID, String testStep, String testMessage, String testStatus) {
        System.out.println(String.format("%s | %s | %s | %s | %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), testCaseID, testStep, testMessage, testStatus));
    }

    public void endTest() {
        System.out.println("End Tests: TestCases");
        driver.quit();
    }

    /**
     * <STRONG>Test Case 01</STRONG>: Verify the URL of the homepage <p>
     *
     *  1. Launch the Amazon India website (https://www.amazon.in/) <p>
     *  2. Make sure that the url contains to the expected title (e.g., "amazon") <p>
     */
    public void testCase01() {
        logStatus("TC001", "Start", "Verify that the URL of the homepage", "DONE");
        driver.get("https://www.amazon.in/");
        String expectedTitle = "amazon";
        String actualTitle = driver.getCurrentUrl();
        boolean status = actualTitle.contains(expectedTitle);
        logStatus("TC001", "Step", "Verify that the URL contains expected title", status ? "PASS" : "FAIL");
        logStatus("TC001", "End", "Verify that the URL of the homepage", status ? "PASS" : "FAIL");
    }

    /**
     * <STRONG>Test Case 02</STRONG>: Verify the search functionality <p>
     *
     *  1. Launch the Amazon India website (https://www.amazon.in/) <p>
     *  2. Locate the search bar using its ID or class name <p>
     *  3. Enter a search term (e.g., "laptop") into the search bar <p>
     *  4. Make sure that the resulting page contains the search term in at least one of the product titles or descriptions <p>
     */
    public void testCase02() {
        logStatus("TC002", "Start", "Verify that the search functionality", "DONE");
        driver.get("https://www.amazon.in/");
        String searchTerm = "laptop";
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys(searchTerm);
        searchBar.submit();
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]//h2//span[contains(text(), 'Laptop')]"));
        boolean status = !products.isEmpty();
        logStatus("TC002", "Step", "Verify that the page shows the search term in a product title or description", status ? "PASS" : "FAIL");
        logStatus("TC002", "End", "Verify that the search functionality", status ? "PASS" : "FAIL");
    }

    /**
     * <STRONG>Test Case 03</STRONG>: Verify the navigation menu <p>
     *
     *  1. Launch the Amazon India website (https://www.amazon.in/) <p>
     *  2. Click on a category from the navigation menu (e.g., "Electronics") <p>
     *  3. Make sure that the resulting page corresponds to the clicked category (e.g., the page url includes "electronics") <p>
     */
    public void testCase03() {
        logStatus("TC003", "Start", "Verify that the navigation menu", "DONE");
        driver.get("https://www.amazon.in/");
        String categoryToSelect = "Electronics";
        WebElement category = driver.findElement(By.xpath("//a[contains(text(), '" + categoryToSelect + "')]"));
        category.click();
        String currentUrl = driver.getCurrentUrl();
        boolean status = currentUrl.contains(categoryToSelect.toLowerCase());
        logStatus("TC003", "Step", "Verify that the resulting page corresponds to the clicked category", status ? "PASS" : "FAIL");
        logStatus("TC003", "End", "Verify that the navigation menu", status ? "PASS" : "FAIL");
    }
}
