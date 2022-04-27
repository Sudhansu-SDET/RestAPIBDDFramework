package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"com.stepDefination"},
        tags = "@DeletePlace",
        plugin = { "pretty" ,"json:target/jsonReports/cucumber-report.json"},
        monochrome = true
)


public class TestRunner {
}