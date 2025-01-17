package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "select[name='group[1]']")
    private WebElement sizeDropdown;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".discount-percentage")
    private WebElement discountPercentage;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = ".cart-content-btn a.btn-primary")
    private WebElement modalCheckoutButton;

    @FindBy(css = "a.btn-primary[href$='order']")
    private WebElement cartPageCheckoutButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void verifyDiscount(int expectedDiscount) {
        wait.until(ExpectedConditions.visibilityOf(discountPercentage));
        String discountText = discountPercentage.getText();
        int actualDiscount = Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
        assert actualDiscount == expectedDiscount :
                "Expected discount: " + expectedDiscount + "% but found: " + actualDiscount + "%";
    }

    public void selectSize(String size) {
        wait.until(ExpectedConditions.elementToBeClickable(sizeDropdown));
        Select sizeSelect = new Select(sizeDropdown);
        sizeSelect.selectByVisibleText(size);
    }

    public void setQuantity(int quantity) {
        try {
            System.out.println("DEBUG: Attempting to set quantity to " + quantity);

            // Wait for quantity input and interact with it
            WebElement qtyInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("quantity_wanted")
            ));

            // Clear the existing value
            qtyInput.clear();

            // Add a small wait after clearing
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Enter the new value
            qtyInput.sendKeys(String.valueOf(quantity));

            System.out.println("DEBUG: Successfully set quantity to " + quantity);
        } catch (Exception e) {
            System.out.println("DEBUG: Error setting quantity: " + e.getMessage());
            throw e;
        }
    }

    public void addToCart() {
        System.out.println("DEBUG: Adding to cart");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".modal-content")
        ));
        System.out.println("DEBUG: Product added to cart");
    }

    public void proceedToCheckout() {
        try {
            System.out.println("DEBUG: Starting checkout process");

            // Wait for and click the modal checkout button
            wait.until(ExpectedConditions.elementToBeClickable(modalCheckoutButton)).click();
            System.out.println("DEBUG: Clicked modal checkout button");

            // Wait for and click the cart page checkout button
            wait.until(ExpectedConditions.elementToBeClickable(cartPageCheckoutButton)).click();
            System.out.println("DEBUG: Clicked cart page checkout button");

        } catch (Exception e) {
            System.out.println("DEBUG: Error during checkout: " + e.getMessage());
            System.out.println("DEBUG: Current URL: " + driver.getCurrentUrl());
            throw e;
        }
    }
}






//Second version
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import java.time.Duration;
//
//public class ProductPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @FindBy(css = "select[name='group[1]']")
//    private WebElement sizeDropdown;
//
//    @FindBy(css = "input.js-cart-line-product-quantity[name='qty']")
//    private WebElement quantityInput;
//
//    @FindBy(css = ".discount-percentage")
//    private WebElement discountPercentage;
//
//    @FindBy(css = "button.add-to-cart")
//    private WebElement addToCartButton;
//
//     Modal elements
//    @FindBy(css = "div.cart-content-btn a.btn-primary")
//    private WebElement modalCheckoutButton;
//
//     Cart summary page elements
//    @FindBy(css = ".cart-summary a.btn-primary")
//    private WebElement cartPageCheckoutButton;
//
//    public ProductPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        PageFactory.initElements(driver, this);
//    }
//
//    public void verifyDiscount(int expectedDiscount) {
//        String discountText = discountPercentage.getText();
//        int actualDiscount = Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
//        assert actualDiscount == expectedDiscount :
//                "Expected discount: " + expectedDiscount + "% but found: " + actualDiscount + "%";
//    }
//
//    public void selectSize(String size) {
//        Select sizeSelect = new Select(sizeDropdown);
//        sizeSelect.selectByVisibleText(size);
//    }
//
//    public void setQuantity(int quantity) {
//         Wait for quantity input to be clickable
//        wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
//        quantityInput.clear();
//        quantityInput.sendKeys(String.valueOf(quantity));
//    }
//
//    public void addToCart() {
//        System.out.println("DEBUG: Adding to cart");
//        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
//    }
//
//    public void proceedToCheckout() {
//        try {
//            System.out.println("DEBUG: Starting checkout process");
//            System.out.println("DEBUG: Current URL before modal: " + driver.getCurrentUrl());
//
//             Wait for modal and click "Proceed to checkout" in modal
//            wait.until(ExpectedConditions.elementToBeClickable(
//                    modalCheckoutButton
//            )).click();
//            System.out.println("DEBUG: Clicked modal checkout button");
//
//
//    }
//}





// FIRST VERSION
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class ProductPage {
//    private WebDriver driver;
//
//    @FindBy(css = "select[name='group[1]']")
//    private WebElement sizeDropdown;
//
//    @FindBy(css = "input[name='qty']")
//    private WebElement quantityInput;
//
//    @FindBy(css = ".discount-percentage")
//    private WebElement discountPercentage;
//
//    @FindBy(css = ".add-to-cart")
//    private WebElement addToCartButton;
//
//     Modal checkout button
//    @FindBy(css = "div.cart-content-btn a.btn-primary")
//    private WebElement modalCheckoutButton;
//
//     Cart page checkout button
//    @FindBy(css = "a.btn-primary[href$='order']")
//    private WebElement cartPageCheckoutButton;
//
//    public ProductPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public void verifyDiscount(int expectedDiscount) {
//        String discountText = discountPercentage.getText();
//        int actualDiscount = Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
//        assert actualDiscount == expectedDiscount :
//                "Expected discount: " + expectedDiscount + "% but found: " + actualDiscount + "%";
//    }
//
//    public void selectSize(String size) {
//        Select sizeSelect = new Select(sizeDropdown);
//        sizeSelect.selectByVisibleText(size);
//    }
//
//    public void setQuantity(int quantity) {
//         Clear existing quantity
//        quantityInput.clear();
//         Set new quantity
//        quantityInput.sendKeys(String.valueOf(quantity));
//    }
//
//    public void addToCart() {
//        System.out.println("DEBUG: Adding to cart");
//        addToCartButton.click();
//
//    }
//
//    public void proceedToCheckout() {
//        System.out.println("DEBUG: Starting checkout process");
//
//        try {
//             Wait for modal to appear and click checkout in modal
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement modalButton = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.cssSelector("div.cart-content-btn a.btn-primary")
//            ));
//            modalButton.click();
//            System.out.println("DEBUG: Clicked modal checkout button");
//
//             Wait for cart page to load and click the second checkout button
//            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.cssSelector("a.btn-primary[href$='order']")
//            ));
//            cartButton.click();
//            System.out.println("DEBUG: Clicked cart page checkout button");
//
//        } catch (Exception e) {
//            System.out.println("DEBUG: Error during checkout: " + e.getMessage());
//            System.out.println("DEBUG: Current URL: " + driver.getCurrentUrl());
//            throw e;
//        }
//    }
//}