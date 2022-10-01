package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends BasePage{
    private By finisBtn = By.id("finish");

    public ConfirmationPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getFinisBtn() {
        return getDriver().findElement(finisBtn);
    }

    public void finishButton(){
        getFinisBtn().click();
    }
}
