package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthenticationPage {
private final WebDriver driver;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
}

    public void loginAs(String email, String password) {
        System.out.println("DEBUG: Starting login process");
        //First I navigate to the login page
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
        System.out.println("DEBUG: Current URL after navigation to login: " + driver.getCurrentUrl());

        //Find and fill email field
        WebElement loginInput = driver.findElement(By.name("email"));
        loginInput.click();    // Make sure field is focused
        loginInput.clear();   // Clear any existing field
        loginInput.sendKeys(email); // Type in the email
        System.out.println("DEBUG: Entered email");

        //Find and fill password field
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        System.out.println("DEBUG: Entered password");

        //Find and click sign in button
        WebElement signInButton = driver.findElement(By.id("submit-login"));
        signInButton.click();
        System.out.println("DEBUG: Clicked sign in button");
        System.out.println("DEBUG: Current URL after login: " + driver.getCurrentUrl());
    }
    // Username Verification
    public String getLoggedUsername() {
        WebElement userName = driver.findElement(By.cssSelector(".account"));
        return userName.getText();

    }
}

// public class AuthenticationPage {
//    private WebDriver driver;
//
//    // Using @FindBy with ID selector - most stable and unique
//    @FindBy(id = "field-email")
//    private WebElement emailInput;
//
//    @FindBy(id = "field-password")
//    private WebElement passwordInput;
//
//    @FindBy(id = "submit-login")
//    private WebElement signInButton;
//
//    public AuthenticationPage(WebDriver driver) {
//        this.driver = driver;
//        // Initialize elements using PageFactory
//        PageFactory.initElements(driver, this);
//    }
//
//    public void login(String email, String password) {
//        emailInput.sendKeys(email);
//        passwordInput.sendKeys(password);
//        signInButton.click();
//    }
//}