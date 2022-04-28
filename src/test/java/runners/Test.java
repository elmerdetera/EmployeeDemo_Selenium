package runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue = { "steps" }, monochrome = true, publish = true, dryRun = false,
// ==================================================FOLDER_or_FILE==================================================
// Per folder.
		features = "src/test/resources/features/",
		// Per feature.
		// features = "src\\test\\resources\\features\\Login\\EWD-3.feature",
		// ==================================================TAGS==================================================
		tags = "@Regression and not @WIP and not @Skip",
		// tags = "@WIP"
		plugin = { "json:target/cucumber-report/cucumber.json" }

)
public class Test {
	@AfterClass
	public static void generateReport() throws Exception {
		String command = "mvn cluecumber-report:reporting";
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.start();
	}
}