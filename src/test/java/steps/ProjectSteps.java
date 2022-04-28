package steps;

import org.junit.Assert;

import helpers.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;

public class ProjectSteps extends Base {
	Pages pages = new Pages();

	// ==================================================Given_Steps==================================================
	@Given("^the user is on the employee login page$")
	public void the_user_is_on_the_employee_login_page() throws Throwable {
		jiraDescription += "\\n - " + "The user is on the employee login page.";

		try {
			if (!webDriver.getTitle().equals(properties.getProperty("pageTitleLogin"))) {
				webElementClick(pages.getPageGeneral().getButton_Logout());
			}

			waitUntilWebElementVisible(pages.getPageLogin().getInput_Username());
			waitUntilWebElementVisible(pages.getPageLogin().getInput_Password());
			waitUntilWebElementVisible(pages.getPageLogin().getButton_Login());

			Assert.assertEquals(properties.getProperty("pageTitleLogin"), getPageTitle());
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getInput_Username()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getInput_Password()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getButton_Login()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@Given("^the user is logged in$")
	public void the_user_is_logged_in() throws Throwable {
		jiraDescription += "\\n - " + "The user is logged in.";

		try {
			if (webDriver.getTitle().equals(properties.getProperty("pageTitleLogin"))) {
				webElementClear(pages.getPageLogin().getInput_Username());
				webElementSendKeys(pages.getPageLogin().getInput_Username(), properties.getProperty("username"));
				webElementClear(pages.getPageLogin().getInput_Password());
				webElementSendKeys(pages.getPageLogin().getInput_Password(), properties.getProperty("password"));
				webElementClick(pages.getPageLogin().getButton_Login());
			}

			waitUntilWebElementVisible(pages.getPageGeneral().getLabel_Username());
			waitUntilWebElementVisible(pages.getPageGeneral().getButton_Logout());

			Assert.assertTrue(isWebElementDisplayed(pages.getPageGeneral().getLabel_Username()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageGeneral().getButton_Logout()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@Given("^the user is on the employee list page$")
	public void the_user_is_on_the_employee_list_page() throws Throwable {
		jiraDescription += "\\n - " + "The user is on the employee list page.";

		try {
			if (webDriver.getTitle().equals(properties.getProperty("pageTitleLogin"))) {
				webElementClear(pages.getPageLogin().getInput_Username());
				webElementSendKeys(pages.getPageLogin().getInput_Username(), properties.getProperty("username"));
				webElementClear(pages.getPageLogin().getInput_Password());
				webElementSendKeys(pages.getPageLogin().getInput_Password(), properties.getProperty("password"));
				webElementClick(pages.getPageLogin().getButton_Login());
			} else if (webDriver.getTitle() != properties.getProperty("pageTitleLogin")) {
				webElementClick(pages.getPageGeneral().getImage_EmployeeLogo());
			}

			waitUntilWebElementVisible(pages.getPageEmployeeList().getLabel_EmployeeListPageTitle());
			waitUntilWebElementVisible(pages.getPageEmployeeList().getButton_SearchEmployee());
			waitUntilWebElementVisible(pages.getPageEmployeeList().getButton_AddEmployee());

			Assert.assertEquals(properties.getProperty("pageTitleEmployeeList"), getPageTitle());
			Assert.assertTrue(isWebElementDisplayed(pages.getPageEmployeeList().getLabel_EmployeeListPageTitle()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageEmployeeList().getButton_SearchEmployee()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageEmployeeList().getButton_AddEmployee()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	// ==================================================When_Steps==================================================
	@When("^the user enters (.*) and (.*) in the credential fields$")
	public void the_user_enters_username_and_password_in_the_credential_fields(String strUsername, String strPassword)
			throws Throwable {
		jiraDescription += "\\n - " + "The user enters \"" + strUsername + "\" and \"" + strPassword
				+ "\" in the credential fields.";

		try {
			webElementClear(pages.getPageLogin().getInput_Username());
			webElementSendKeys(pages.getPageLogin().getInput_Username(), strUsername);
			webElementClear(pages.getPageLogin().getInput_Password());
			webElementSendKeys(pages.getPageLogin().getInput_Password(), strPassword);
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@When("^the user clicks the login button$")
	public void the_user_clicks_the_login_button() throws Throwable {
		jiraDescription += "\\n - " + "The user clicks the login button.";

		try {
			webElementClick(pages.getPageLogin().getButton_Login());
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@When("^the user logs out$")
	public void the_user_logs_out() throws Throwable {
		jiraDescription += "\\n - " + "The user logs out.";

		try {
			webElementClick(pages.getPageGeneral().getButton_Logout());
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	// ==================================================Then_Steps==================================================
	@Then("^the user (.*) will be logged in successfully$")
	public void the_user_will_be_logged_in_successfully(String strUsername) throws Throwable {
		jiraDescription += "\\n - " + "The user \"" + strUsername + "\" will be logged in successfully.";

		try {
			waitUntilWebElementVisible(pages.getPageGeneral().getLabel_Username());
			waitUntilWebElementVisible(pages.getPageGeneral().getButton_Logout());

			Assert.assertEquals(properties.getProperty("pageTitleEmployeeList"), getPageTitle());
			Assert.assertTrue(isWebElementDisplayed(pages.getPageGeneral().getLabel_Username()));
			Assert.assertEquals(strUsername, getWebElementText(pages.getPageGeneral().getLabel_Username()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageGeneral().getButton_Logout()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@Then("^the user will be back on the employee login page$")
	public void the_user_will_be_back_on_the_employee_login_page() throws Throwable {
		jiraDescription += "\\n - " + "The user will be back on the employee login page.";

		try {
			waitUntilWebElementVisible(pages.getPageLogin().getInput_Username());
			waitUntilWebElementVisible(pages.getPageLogin().getInput_Password());
			waitUntilWebElementVisible(pages.getPageLogin().getButton_Login());

			Assert.assertEquals(properties.getProperty("pageTitleLogin"), getPageTitle());
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getInput_Username()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getInput_Password()));
			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getButton_Login()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}

	@Then("^the feedback message (.*) will be displayed$")
	public void the_feedback_message_will_be_displayed(String strFeedbackMessage) throws Throwable {
		jiraDescription += "\\n - " + "The feedback message \"" + strFeedbackMessage + "\" will be displayed.";

		try {
			strFeedbackMessage = strFeedbackMessage.replace("\"", "");

			waitUntilWebElementVisible(pages.getPageLogin().getLabel_FeedbackMessage());

			Assert.assertTrue(isWebElementDisplayed(pages.getPageLogin().getLabel_FeedbackMessage()));
			Assert.assertEquals(strFeedbackMessage, getWebElementText(pages.getPageLogin().getLabel_FeedbackMessage()));
		} catch (AssertionError e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			jiraSummary = e.getMessage().toString();
			throw new Exception(e.getMessage());
		}
	}
}