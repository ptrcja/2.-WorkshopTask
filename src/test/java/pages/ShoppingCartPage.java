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
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();

    }

    public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOf(totalAmount)).getText();
    }
}



