package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(menuButton).click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        driver.findElement(logoutLink).click();
    }
}

