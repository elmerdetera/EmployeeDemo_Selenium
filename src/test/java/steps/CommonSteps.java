package steps;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.sun.jersey.core.util.Base64;

import helpers.Base;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CommonSteps extends Base {
	@Before
	public void jiraBeforeScenario(Scenario scenario) throws Exception {
		try {
			jiraID = scenario.getId().substring(scenario.getId().indexOf("EWD-"),
					scenario.getId().indexOf(".feature:"));
			jiraSummary = null;
			jiraDescription = "Steps to Replicate:";
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@After
	public void jiraAfterScenario(Scenario scenario) throws Exception {
		try {
			if (!scenario.getName().equals("Preparation") && scenario.isFailed()) {
				// ==================================================Screenshot==================================================
//				String strFilePath = "";
//				strFilePath = takeScreenshot(
//						jiraID + "-S" + scenario.getName().substring(0, scenario.getName().indexOf(":")) + "-"
//								+ getStringDateTimeNow("dd.MM.yyyy_HHmm"));
//				strFilePath = strFilePath.substring(strFilePath.indexOf("screenshots"), strFilePath.length());

				// ==================================================Raise_to_JIRA==================================================
				String jiraAuthentication = new String(Base64
						.encode(properties.getProperty("jiraEmail") + ":" + properties.getProperty("jiraAPIKey")));

				CloseableHttpClient client = HttpClientBuilder.create().build();
				HttpPost httpPost = new HttpPost(properties.getProperty("jiraRestURL"));
				httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + jiraAuthentication);
				httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
				httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
				String body = "{\"fields\":{\"project\":{\"key\":\"" + properties.getProperty("jiraProjectKey")
						+ "\"},\"summary\":\"" + jiraSummary + "\",\"description\":\""
						+ jiraDescription.toString().replace("\"", "")
						+ "\",\"issuetype\":{\"name\":\"Bug\"}},\"update\":{\"issuelinks\":[{\"add\":{\"type\":{\"name\":\"Relates\",\"inward\":\"relates to\",\"outward\":\"relates to\"},\"outwardIssue\":{\"key\":\""
						+ jiraID + "\"}}}]}}";
				StringEntity entity = new StringEntity(body);
				httpPost.setEntity(entity);

				HttpResponse jiraResponse = client.execute(httpPost);
				String responseBody = EntityUtils.toString(jiraResponse.getEntity());
//				int statusCode = jiraResponse.getStatusLine().getStatusCode();

				System.out.println("JIRA Details: " + responseBody);
			}
		} catch (AssertionError e) {
			throw new Exception("Unable to execute after scenario statements." + "\n" + "Cause: " + e.getMessage());
		}
	}

	@Before("@Start")
	public void webDriverBeforeScenario(Scenario scenario) throws Exception {
		try {
			Base.initialization();
			waitUntilWebBrowserTitleContains(properties.getProperty("pageTitleLogin"));

			Assert.assertTrue(getCurrentUrl().equals(properties.getProperty("url")));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@After("@End")
	public void webDriverAfterScenario(Scenario scenario) throws Exception {
		try {
			webDriver.quit();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@AfterStep
	public void screenshot(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				scenario.log("Current Page URL is " + webDriver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "AttachedScreenshots");
			}

		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}
}