package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginActions {
    WebDriver driver;

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");

    public LoginActions(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();

        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutLink.click();
    }
}

