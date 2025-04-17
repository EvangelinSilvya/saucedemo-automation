package Base;
import Utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentManager.createTest(result.getName()).fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentManager.createTest(result.getName()).pass("Test passed");
        }
        ExtentManager.flush();

        if (driver != null) {
            driver.quit();
        }
    }
}


