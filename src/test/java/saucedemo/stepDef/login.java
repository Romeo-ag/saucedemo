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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class login {
//    public Duration duration = Duration.ofSeconds(10);

    WebDriver driver;
//    WebDriverWait wait = new WebDriverWait(driver, duration); //create object
    String baseUrl = "https://www.saucedemo.com";
    @Given("Login page of saucedemo")
    public void loginPageOfSaucedemo() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
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

    @Then("user verify (.*) login result$")
    public void userVerifyStatusLoginResult(String status) {
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS); // set imeout for web drive on waiting elemnt
        String errorLogin ;
        switch (status){
            case "success" :
//              String logout_button= driver.findElement(By.id('logout_sidebar_link')).getText();
                String logout_button  = driver.findElement(By.id("logout_sidebar_link")).getText();
                Assert.assertEquals(logout_button, "Logout");
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

    }
}
