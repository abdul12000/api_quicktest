package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files/",
        plugin={"html:target/ReportsDestination", "pretty"}, monochrome = true, tags="@mirene", glue={"Step_definitions"})
public class TestRunner {
}
