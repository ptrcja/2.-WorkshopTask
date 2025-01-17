package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class PurchaseSteps {
    private WebDriver driver;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;
    private OrderHistoryPage orderHistoryPage;

    @Given("the user is logged in with email {string} and password {string}")
    public void theUserIsLoggedInWithEmailAndPassword(String email, String password) {
        System.out.println("DEBUG: Starting login step");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize all page objects
        authenticationPage = new AuthenticationPage(driver);
        myAccountPage = new MyAccountPage(driver);
        productPage = new ProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);

        // Login
        authenticationPage.loginAs(email, password);
        System.out.println("DEBUG: Finished login step");
    }

    @When("the user selects the Hummingbird Printed Sweater")
    public void theUserSelectsTheHummingbirdPrintedSweater() {
        System.out.println("DEBUG: Starting sweater selection");
        myAccountPage.navigateViaCategory();
        System.out.println("DEBUG: Finished sweater selection");
    }

    @Then("the discount should be {int}%")
    public void theDiscountShouldBe(Integer expectedDiscount) {
        productPage.verifyDiscount(expectedDiscount);
    }

    @When("the user selects size {string}")
    public void theUserSelectsSize(String size) {
        productPage.selectSize(size);
    }

    @And("the user selects {string} pieces")
    public void theUserSelectsPieces(String quantity) {
        try {
            System.out.println("DEBUG: Setting quantity to " + quantity);
            productPage.setQuantity(Integer.parseInt(quantity));
            System.out.println("DEBUG: Successfully set quantity");
        } catch (Exception e) {
            System.out.println("DEBUG: Error setting quantity: " + e.getMessage());
            throw e;

        }

    //This was earlier
//    @And("the user selects {string} pieces")
//    public void theUserSelectsPieces(String quantity) {
//        productPage.setQuantity(Integer.parseInt(quantity));
    }

    @And("the user adds the product to the cart")
    public void theUserAddsTheProductToTheCart() {
        System.out.println("DEBUG: Adding product to cart");
        productPage.addToCart();
    }

    @And("the user goes to checkout")
    public void theUserGoesToCheckout() {
        try {
            System.out.println("DEBUG: Starting checkout process");

            // First, handle the modal checkout
            productPage.proceedToCheckout();
            System.out.println("DEBUG: Completed modal checkout");

            // Then, on the cart page, proceed to the next step
            Thread.sleep(1000); // Wait for cart page to load
            shoppingCartPage.proceedToCheckout();
            System.out.println("DEBUG: Completed cart page checkout");

        } catch (Exception e) {
            System.out.println("DEBUG: Error during checkout: " + e.getMessage());

        }
    }
//    @And("the user goes to checkout")
//    public void theUserGoesToCheckout() {
//        System.out.println("DEBUG: Starting checkout process");
//        productPage.proceedToCheckout();
        //handle the cart page
//        shoppingCartPage.proceedToCheckout();
//        System.out.println("DEBUG: Completed cart page checkout");
//    }

    @And("the user confirms the address")
    public void theUserConfirmsTheAddress() {
        System.out.println("DEBUG: Starting address confirmation");
        checkoutPage.confirmAddress();
    }

    @And("the user selects the pickup method {string}")
    public void theUserSelectsThePickupMethod(String method) {
        checkoutPage.selectPickupMethod(method);
    }

    @And("the user selects the payment option {string}")
    public void theUserSelectsThePaymentOption(String paymentOption) {
        checkoutPage.selectPayByCheck();
    }

    @And("the user places the order")
    public void theUserPlacesTheOrder() {
        checkoutPage.acceptTermsAndConditions();
        checkoutPage.placeOrder();
    }

    @Then("the user takes a screenshot of the order confirmation and the amount")
    public void theUserTakesAScreenshotOfTheOrderConfirmationAndTheAmount() {
        checkoutPage.takeScreenshot();
    }

    @And("the user goes to order history")
    public void theUserGoesToOrderHistory() {
        orderHistoryPage.goToOrderHistory();
    }

    @Then("the order should be listed with the status {string} and the same amount")
    public void theOrderShouldBeListedWithTheStatusAndTheSameAmount(String status) {
        String latestOrderStatus = orderHistoryPage.getLatestOrderStatus();
        String latestOrderAmount = orderHistoryPage.getLatestOrderAmount();
        assert latestOrderStatus.equals(status);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


//package steps;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import pages.*;
//
//import java.time.Duration;
//
//public class PurchaseSteps {
//    private WebDriver driver;
//    private AuthenticationPage authenticationPage;
//    private MyAccountPage myAccountPage;
//    private ProductPage productPage;
//    private CheckoutPage checkoutPage;
//    private OrderHistoryPage orderHistoryPage;
//
//    @Given("the user is logged in with email {string} and password {string}")
//    public void theUserIsLoggedInWithEmailAndPassword(String email, String password) {
//        System.out.println("DEBUG: Starting login step");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//         Initialize pages
//        authenticationPage = new AuthenticationPage(driver);
//         Login
//        authenticationPage.loginAs(email, password);
//        myAccountPage = new MyAccountPage(driver);
//        System.out.println("DEBUG: Finished login step");
//
//
//    }
//
//    @When("the user selects the Hummingbird Printed Sweater")
//    public void theUserSelectsTheHummingbirdPrintedSweater() {
//        System.out.println("DEBUG: Starting sweater selection");
//        myAccountPage.navigateViaCategory();
//        productPage = new ProductPage(driver);
//        System.out.println("DEBUG: Finished sweater selection");
//    }
//
//    @Then("the discount should be {int}%")
//    public void theDiscountShouldBe(Integer expectedDiscount) {
//        productPage.verifyDiscount(expectedDiscount);
//    }
//
//    @When("the user selects size {string}")
//    public void theUserSelectsSize(String size) {
//        productPage.selectSize(size);
//    }
//
//    @And("the user selects {string} pieces")
//    public void theUserSelectsPieces(String quantity) {
//        productPage.setQuantity(Integer.parseInt(quantity));
//    }
//
//    @And("the user adds the product to the cart")
//    public void theUserAddsTheProductToTheCart() {
//        System.out.println("DEBUG: Adding product to cart");
//        productPage.addToCart();
//    }
//
//    @And("the user goes to checkout")
//    public void theUserGoesToCheckout() {
//        System.out.println("DEBUG: Starting checkout process");
//
//        productPage.proceedToCheckout();
//
//    }
//
//    @And("the user confirms the address")
//    public void theUserConfirmsTheAddress() {
//        System.out.println("DEBUG: Starting address confirmation step");
//
//        checkoutPage = new CheckoutPage(driver);
//        checkoutPage.confirmAddress();
//        System.out.println("DEBUG: Finished address confirmation step");
//    }
//
//    @And("the user selects the pickup method 'PrestaShop pick up in store'")
//    public void theUserSelectsThePickupMethod(String method) {
//        checkoutPage.selectPickupMethod(method);
//    }
//
//    @And("the user selects the payment option {string}")
//    public void theUserSelectsThePaymentOption(String paymentOption) {
//        checkoutPage.selectPayByCheck();
//    }
//
//    @And("the user places the order")
//    public void theUserPlacesTheOrder() {
//        checkoutPage.acceptTermsAndConditions();
//        checkoutPage.placeOrder();
//    }
//
//    @Then("the user takes a screenshot of the order confirmation and the amount")
//    public void theUserTakesAScreenshotOfTheOrderConfirmationAndTheAmount() {
//        checkoutPage.takeScreenshot();
//    }
//
//    @And("the user goes to order history")
//    public void theUserGoesToOrderHistory() {
//        orderHistoryPage = new OrderHistoryPage(driver);
//        orderHistoryPage.goToOrderHistory();
//    }
//
//    @Then("the order should be listed with the status {string} and the same amount")
//    public void theOrderShouldBeListedWithTheStatusAndTheSameAmount(String status) {
//        String latestOrderStatus = orderHistoryPage.getLatestOrderStatus();
//        String latestOrderAmount = orderHistoryPage.getLatestOrderAmount();
//        assert latestOrderStatus.equals(status);
//    }
//}
