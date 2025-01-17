package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
    private WebDriver driver;

    @FindBy(css = ".account")
    private WebElement userAccountButton;

    @FindBy(id = "history-link")
    private WebElement orderHistoryTile;

    @FindBy(css = "span.label.label-pill.bright")
    private WebElement latestOrderStatus;

    @FindBy(css = "td.text-xs-right")
    private WebElement latestOrderAmount;


    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToOrderHistory() {
        userAccountButton.click();
        orderHistoryTile.click();
    }

    public String getLatestOrderStatus() {
        return latestOrderStatus.getText();
    }

    public String getLatestOrderAmount() {
        return latestOrderAmount.getText();
    }

}
