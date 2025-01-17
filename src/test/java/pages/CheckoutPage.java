package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.File;

public class CheckoutPage {
    private final WebDriver driver;

    @FindBy(css = "button.continue[name='confirm-addresses']")
    private WebElement confirmAddressButton;

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
        PageFactory.initElements(driver, this);
    }

    public void confirmAddress() {
        confirmAddressButton.click();
    }

    public void selectPickupMethod(String method) {
        confirmDeliveryOptionButton.click();
    }

    public void selectPayByCheck() {
        payByCheckOption.click();
    }

    public void acceptTermsAndConditions() {
        termsAndConditionsCheckbox.click();
    }

    public void placeOrder() {
        orderButton.click();
    }

    public void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("order_confirmation_" + System.currentTimeMillis() + ".png");
        screenshot.renameTo(destinationFile);
    }
}