package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.*;



import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class login {
//    Duration duration = Duration.ofSeconds(10);

    WebDriver driver;
//    WebDriverWait wait = new WebDriverWait(driver, duration); //create object
    String baseUrl = "https://www.saucedemo.com";
    @Given("Login page of saucedemo")
    public void loginPageOfSaucedemo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(baseUrl);

        //Assertion
        String loginPageAssert = driver.findElement(By.xpath("//h4[contains(text(),'Accepted usernames are:')]")).getText();
        Assert.assertEquals(loginPageAssert,"Accepted usernames are:");
    }

    @When("User input (.*) in username field$")
    public void userInputUsernameInUsernameField(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("User input (.*) in password field$")
    public void userInputPasswordInPasswordField(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }

    @Then("User add item to cart")
    public void userAddItemToCart(io.cucumber.datatable.DataTable dataTable) {
//        String item = "add-to-cart-sauce-labs-backpack";
        List<String> Item =dataTable.asList(String.class);
        for(String item: Item) {
        System.out.println(item);
//        while(driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed()) {
            System.out.println("found");
            driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).click();
//            driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
//            break;
//        }
//        while(driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).isDisplayed()) {
            String card_cart = driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).getText();
            Assert.assertEquals(card_cart, "Remove");
//            break;
//        }
        }

    }
    @Then("user verify (.*) login result$")
    public void userVerifyStatusLoginResult(String status) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String errorLogin ;
        switch (status){
            case "success" :
                driver.findElement(By.xpath("//*[@id='react-burger-menu-btn']")).click();

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }

                System.out.println("Sidebar found");


                driver.findElement(By.id("logout_sidebar_link")).isDisplayed();
                    System.out.println("Sidebar found");
                    String logout_button = driver.findElement(By.id("logout_sidebar_link")).getText();
                    Assert.assertEquals(logout_button, "Logout");
                                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                driver.findElement(By.xpath("//*[@id='react-burger-cross-btn']")).isDisplayed();
                driver.findElement(By.xpath("//*[@id='react-burger-cross-btn']")).click();


                break;
            case "invalid_pass" :
                 errorLogin = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
                Assert.assertEquals(errorLogin, "Epic sadface: Username and password do not match any user in this service");
                break;
            case "locked" :
                 errorLogin = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
                Assert.assertEquals(errorLogin, "Epic sadface: Sorry, this user has been locked out.");
                break;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

//        driver.close();

    }



    @Then("User remove item from cart")
    public void userRemoveItemFromCart(io.cucumber.datatable.DataTable dataTable) {
//        Wait to see transition from "Add to Cart" to 'Remove" visually
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<String> Item =dataTable.asList(String.class);
        for(String item: Item) {

            System.out.println(item);
//        if(driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed()) {
//
//            driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).click();
//
//            break;
//        }

//        while(driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).isDisplayed()) {
            driver.findElement(By.xpath("//*[@id='remove-" + item + "']")).isDisplayed();
            driver.findElement(By.xpath("//*[@id='remove-" + item + "']")).click();
            System.out.println("remove detected");
//            break;
//        }
//        while(driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed()) {
//         if(driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed()) {
            driver.findElement(By.xpath("//*[@id='add-to-cart-" + item + "']")).isDisplayed();
            System.out.println("add to cart detected");
            String card_cart = driver.findElement(By.xpath("//*[@id='add-to-cart-" + item + "']")).getText();
            Assert.assertEquals(card_cart, "Add to cart");
//            Assert.a
        }
//            break;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
//         driver.close();
    }

    @Then("user click cart icon")
    public void userClickCartIcon() {
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']")).click();


    }

    @Then("User click burger button")
    public void userClickBurgerButton() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElement(By.xpath("//*[@id='react-burger-menu-btn']")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='react-burger-menu-btn']")).click();
    }

    @Then("User click Logout button")
    public void userClickLogoutButton() {


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

                driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).isDisplayed();
            driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();


    }

    @Then("User verify login page of saucedemo")
    public void userVerifyLoginPageOfSaucedemo() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        driver.findElement(By.xpath("//*[@id='login-button']")).isDisplayed();
        String login = driver.findElement(By.xpath("//input[@type='submit']")).getAttribute("value");
        Assert.assertEquals(login, "Login");

    }







    @Then("User click Continue")
    public void userClickContinue() {
        driver.findElement(By.id("continue")).click();

    }

    @Then("User verify Checkout Complete Page")
    public void userVerifyCheckoutCompletePage() {

        String completeOrder = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]")).getText();
        Assert.assertEquals(completeOrder,"Thank you for your order!");

    }

    @And("user input First Name")
    public void userInputFirstName() {
        driver.findElement(By.id("first-name")).sendKeys("Nama Pertama");
    }

    @And("User input Last name")
    public void userInputLastName() {
        driver.findElement(By.id("last-name")).sendKeys("Nama Keluarga");
    }

    @And("User input Postal Code")
    public void userInputPostalCode() {
        driver.findElement(By.id("postal-code")).sendKeys("12345");

    }

    @Then("User click finish")
    public void userClickFinish() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("User click checkout")
    public void userClickCheckout() {
        driver.findElement(By.xpath("//*[@id='checkout']")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='checkout']")).click();

    }


    @Then("User add to card")
    public void userAddItemToCard(io.cucumber.datatable.DataTable dataTable) {
        List<String> Item =dataTable.asList(String.class);
        for(String item: Item){
            System.out.println(item);
//        String item = "add-to-cart-sauce-labs-backpack";
//            System.out.println(item);
//            while(driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed()) {
            driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).isDisplayed();
                System.out.println("found");

                driver.findElement(By.xpath("//*[@id='add-to-cart-"+item+"']")).click();
//            driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
//                break;
//            }
//            while(driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).isDisplayed()) {
                driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).isDisplayed();
                String card_cart = driver.findElement(By.xpath("//*[@id='remove-"+item+"']")).getText();
                Assert.assertEquals(card_cart, "Remove");
//                break;
//            }


        }
    }


}
