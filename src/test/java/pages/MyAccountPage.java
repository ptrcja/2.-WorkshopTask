package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
private WebDriver driver;


@FindBy(css = "a[href*='category=3']")
    private WebElement clothesCategory;

@FindBy(linkText = "Hummingbird Printed Sweater")
    private WebElement sweaterLink;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

public void navigateViaCategory() {
    System.out.println("DEBUG: Starting category navigation");
    System.out.println("DEBUG: Current URL before home navigation: " + driver.getCurrentUrl());

    driver.get("https://mystore-testlab.coderslab.pl/index.php");
    System.out.println("DEBUG: Current URL after home navigation: " + driver.getCurrentUrl());

    System.out.println("DEBUG: Trying to click clothes category");
    clothesCategory.click();
    System.out.println("DEBUG: Current URL after clicking clothes: " + driver.getCurrentUrl());

    System.out.println("DEBUG: Trying to click sweater link");
    sweaterLink.click();
    System.out.println("DEBUG: Current URL after clicking sweater: " + driver.getCurrentUrl());


    }

}
