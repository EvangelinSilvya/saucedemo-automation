package Tests;
import actions.LoginActions;
import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.TestDataProvider;

import java.time.Duration;

public class SauceTests extends BaseTest {

    @Test(dataProvider = "loginData",  dataProviderClass = TestDataProvider.class)
    public void testLoginLogout(String username, String password, boolean shouldLogin) {
        LoginActions actions = new LoginActions(driver);
        actions.login(username, password);

        boolean isLoggedIn = driver.getCurrentUrl().contains("inventory");

        if (shouldLogin) {
            Assert.assertTrue(isLoggedIn, "Login failed for valid credentials.");
            // Check cookie value
            String cookie = driver.manage().getCookieNamed("session-username").getValue();
            Assert.assertEquals(cookie, username, "Session username mismatch.");
            actions.logout();
        } else {
            Assert.assertFalse(isLoggedIn, "Login should not succeed for invalid credentials.");
        }
    }

    @Test
    public void testNavigation() {
        new LoginActions(driver).login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Failed to navigate to inventory page.");
    }

    @Test
    public void testHeaderValidation() {
        new LoginActions(driver).login("standard_user", "secret_sauce");
        String header = driver.findElement(By.className("app_logo")).getText();
        Assert.assertEquals(header, "Swag Labs", "Header text does not match.");
    }

    @Test
    public void testLocalStorageAfterLogin() throws InterruptedException {
        new LoginActions(driver).login("standard_user", "secret_sauce");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.manage().getCookieNamed("session-username") != null);

        Cookie userCookie = driver.manage().getCookieNamed("session-username");

        if (userCookie != null) {
            String actualUser = userCookie.getValue();
            System.out.println("Cookie Value: " + actualUser);
            Assert.assertEquals(actualUser, "standard_user");
        } else {
            Assert.fail("session-username cookie was not found after login.");
        }
    }

    @Test
    public void testNegativeLogin() {
        new LoginActions(driver).login("wrong_user", "wrong_pass");
        boolean errorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        Assert.assertTrue(errorDisplayed, "Error should be displayed for invalid login.");
    }
}
