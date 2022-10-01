package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{
    private By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By cart = By.xpath("//*[@id='shopping_cart_container']/a");


    public ProductPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getAddToCart() {
        return getDriver().findElement(addToCartBtn);
    }

    public WebElement getCart() {
        return getDriver().findElement(cart);
    }

    public void add(){
        getAddToCart().click();
    }

    public void cartValue(){
        getCart().click();
    }
}
