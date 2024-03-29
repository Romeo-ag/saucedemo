package saucedemo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/saucedemo/features",
        glue = "saucedemo.stepDef",
        plugin = {"html:target/HTML_report.html"},
        tags = "@Login or @Cart or  @Checkout or @Logout"
)
public class runLogin {
}
