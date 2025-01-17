package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ShoppingCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a.btn-primary[href$='order']")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".cart-summary-line.cart-total .value")
    private WebElement totalAmount;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void proceedToCheckout() {
        System.out.println("DEBUG: On cart page, proceeding to checkout");
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        System.out.println("DEBUG: Clicked cart page checkout button");
    }

    public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOf(totalAmount)).getText();
    }
}

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class ShoppingCartPage {
//    private WebDriver driver;
//
//    @FindBy(css = ".cart-summary-line.cart-total .value")
//    private WebElement totalAmount;
//
//    @FindBy(css = "a.btn.btn-primary[href*='order']")
//    private WebElement proceedToCheckoutButton;
//
//    @FindBy(css = "input.js-cart-line-product-quantity")
//    private WebElement productQuantity;
//
//    @FindBy(css = ".product-line-info .value")
//    private WebElement productSize;
//
//    public ShoppingCartPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public String getTotalAmount() {
//        return totalAmount.getText();
//    }
//right one below
//    public void proceedToCheckout() {
//        proceedToCheckoutButton.click();
//
//    delete
//    public void proceedToCheckout() {
//        System.out.println("DEBUG: Starting checkout process");
//        System.out.println("DEBUG: Current URL before checkout: " + driver.getCurrentUrl());
//        proceedToCheckoutButton.click();
//        System.out.println("DEBUG: Current URL after checkout: " + driver.getCurrentUrl());
//    }
//
//    public String getProductQuantity() {
//        return productQuantity.getAttribute("value");
//    }
//
//    public String getProductSize() {
//        return productSize.getText();
//    }
//
//
//    }
