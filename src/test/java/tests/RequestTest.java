package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class RequestTest {
    private WebDriver driver;
    private WebDriverWait driverWait;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;
    private LogoutPage logoutPage;



    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "/Users/macbook/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver, driverWait);
        productPage = new ProductPage(driver, driverWait);
        checkoutPage = new CheckoutPage(driver, driverWait);
        confirmationPage = new ConfirmationPage(driver,driverWait);
        logoutPage = new LogoutPage(driver,driverWait);

    }

    @Test (priority = 1)
    public void login(){
        Assert.assertTrue(loginPage.getUsername().isDisplayed());
        Assert.assertTrue(loginPage.getPassword().isDisplayed());
        Assert.assertTrue(loginPage.getLoginBtn().isDisplayed());
        loginPage.login("standard_user", "secret_sauce");
        String expectedResult = "PRODUCTS";
        WebElement actualResult = driver.findElement(By.className("title"));
        Assert.assertEquals(actualResult.getText(), expectedResult);

    }

    @Test (priority = 2)
    public void addToCart() {
        productPage.add();
        String expectedResult = "REMOVE";
        WebElement actualResult = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(expectedResult, actualResult.getText());
        productPage.cartValue();
        String expectedRes = "1";
        WebElement actualRes = driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]/div[1]"));
        Assert.assertEquals(expectedRes, actualRes.getText());

    }

    @Test (priority = 3)
    public void checkout(){
        Assert.assertTrue(checkoutPage.getCheckoutBtn().isDisplayed());
        checkoutPage.checkoutBtn();
        String expectedResult = "CHECKOUT: YOUR INFORMATION";
        WebElement actualResult = driver.findElement(By.className("title"));
        Assert.assertEquals(expectedResult, actualResult.getText());
        checkoutPage.fillForm("Lujza", "Veres", "24000");
        String expectedRes = "CHECKOUT: OVERVIEW";
        WebElement actualRes = driver.findElement(By.className("title"));
        Assert.assertEquals(expectedRes, actualRes.getText());

    }

    @Test (priority = 4)
    public void finish(){
        String expectedResult = "Total: $32.39";
        WebElement actualResult = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[7]"));
        Assert.assertEquals(expectedResult, actualResult.getText());
        confirmationPage.finishButton();
        String expectedRes = "CHECKOUT: COMPLETE!";
        WebElement actualRes = driver.findElement(By.className("title"));
        Assert.assertEquals(expectedRes, actualRes.getText());
    }

    @Test (priority = 5)
    public void logout() throws InterruptedException {
        logoutPage.sideBarBtn();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        logoutPage.logoutBtn();
        Assert.assertTrue(loginPage.getUsername().isDisplayed());
        Assert.assertTrue(loginPage.getPassword().isDisplayed());
        Assert.assertTrue(loginPage.getLoginBtn().isDisplayed());
        driver.get("https://www.saucedemo.com/cart.html");
        String expectedResult = "Epic sadface: You can only access '/cart.html' when you are logged in.";
        WebElement actualResult = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));
        Assert.assertEquals(expectedResult, actualResult.getText());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
