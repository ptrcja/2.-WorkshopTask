package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class CheckoutPage {
        private WebDriver driver;
        private WebDriverWait wait;

        @FindBy(css = "button.continue[name='confirm-addresses']")
        private WebElement confirmAddressButton;

        @FindBy(id = "delivery_option_1")
        private WebElement pickupInStoreOption;

        @FindBy(css = "button[name='confirmDeliveryOption']")
        private WebElement confirmDeliveryOptionButton;

        @FindBy(id = "payment-option-1")
        private WebElement payByCheckOption;

        @FindBy(id = "conditions_to_approve[terms-and-conditions]")
        private WebElement termsAndConditionsCheckbox;

        @FindBy(css = "button.btn.btn-primary.center-block")
        private WebElement orderButton;

        public CheckoutPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(driver, this);
        }


          public void confirmAddress() {
              System.out.println("DEBUG: Starting address confirmation");
              try {
                  // Wait for the address form to be present first
                  wait.until(ExpectedConditions.presenceOfElementLocated(
                          By.cssSelector(".js-address-form")
                  ));

                  // Click continue button using a more specific selector
                  WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                          By.cssSelector("button[name='confirm-addresses']")
                  ));
                  continueBtn.click();
                  System.out.println("DEBUG: Successfully confirmed address");
              } catch (Exception e) {
                  System.out.println("DEBUG: Failed to confirm address: " + e.getMessage());
                  System.out.println("DEBUG: Current URL: " + driver.getCurrentUrl());
                  throw e;
              }
          }

        public void selectPickupMethod(String method) {
            wait.until(ExpectedConditions.elementToBeClickable(pickupInStoreOption)).click();
            wait.until(ExpectedConditions.elementToBeClickable(confirmDeliveryOptionButton)).click();
        }

        public void selectPayByCheck() {
            wait.until(ExpectedConditions.elementToBeClickable(payByCheckOption)).click();
        }

        public void acceptTermsAndConditions() {
            wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox)).click();
        }

        public void placeOrder() {
            wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        }

        public void takeScreenshot() {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destinationFile = new File("order_confirmation_" +
                        System.currentTimeMillis() + ".png");
                screenshot.renameTo(destinationFile);
            } catch (Exception e) {
                System.out.println("Failed to take screenshot: " + e.getMessage());
            }
        }
    }
