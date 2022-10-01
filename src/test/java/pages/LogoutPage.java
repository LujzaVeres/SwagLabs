package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasePage{
    private By sideBar = By.id("react-burger-menu-btn");
    private By logoutBtn = By.id("logout_sidebar_link");

    public LogoutPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getSideBar() {
        return getDriver().findElement(sideBar);
    }

    public WebElement getLogoutBtn() {
        return getDriver().findElement(logoutBtn);
    }

    public void sideBarBtn(){
        getSideBar().click();
    }
    public void logoutBtn(){
        getLogoutBtn().click();
    }
}
