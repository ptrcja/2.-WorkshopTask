package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",    // where your .feature files are
        glue = "steps",                         // where your step definitions are
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class PurchaseStepsTest {
}
