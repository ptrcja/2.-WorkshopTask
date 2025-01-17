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

    // Modal checkout button
    @FindBy(css = "div.cart-content-btn a.btn-primary")
    private WebElement modalCheckoutButton;

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
    public void setQuantity(int targetQuantity) {
        try {
            System.out.println("DEBUG: Attempting to set quantity to " + targetQuantity);
            // Wait for the increment button to be clickable
            WebElement incrementButton = wait.until(ExpectedConditions
                    .elementToBeClickable(By.cssSelector("button.bootstrap-touchspin-up")));

            // Get current quantity
            int clicksNeeded = targetQuantity;
            for (int i = 0; i < clicksNeeded; i++) {
                incrementButton.click();
                // Small wait to ensure click is registered
               Thread.sleep(200);
            }

            // Verify final quantity
            WebElement qtyInput = driver.findElement(By.id("quantity_wanted"));
            String finalQuantity = qtyInput.getAttribute("value");
            System.out.println("DEBUG: Final quantity set to: " + finalQuantity);

        } catch (Exception e) {
            System.out.println("DEBUG: Error setting quantity: " + e.getMessage());
            throw new RuntimeException("Error setting quantity: " + e.getMessage());
        }
    }

    public void addToCart() {
        try {
            System.out.println("DEBUG: Attempting to add to cart");
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            System.out.println("DEBUG: Add to cart button clicked");

            // Wait for modal
            System.out.println("DEBUG: Waiting for modal");
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.cart-content-btn")
            ));
            System.out.println("DEBUG: Modal appeared");
        } catch (Exception e) {
            System.out.println("DEBUG: Error adding to cart: " + e.getMessage());
            throw e;
        }
    }

    public void proceedToCheckout() {
        try {
            System.out.println("DEBUG: Starting checkout process");
            System.out.println("DEBUG: Current URL: " + driver.getCurrentUrl());

            // Click the modal's PROCEED TO CHECKOUT button
            WebElement modalButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div.cart-content-btn a.btn-primary[href*='controller=cart']")
            ));
            modalButton.click();
            System.out.println("DEBUG: Clicked modal checkout button");

        } catch (Exception e) {
            System.out.println("DEBUG: Error during checkout: " + e.getMessage());
            System.out.println("DEBUG: Current URL: " + driver.getCurrentUrl());
            try {
                System.out.println("DEBUG: Available buttons on page:");
                driver.findElements(By.cssSelector("a.btn-primary")).forEach(button -> {
                    System.out.println("Button text: " + button.getText());
                    System.out.println("Button href: " + button.getAttribute("href"));
                });
            } catch (Exception ex) {
                System.out.println("DEBUG: Error while listing buttons: " + ex.getMessage());
            }
            throw e;
        }
    }
}



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