package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Base;

public class Page_Login extends Base {
	// ==================================================Initialize_Page_Objects==================================================
	public Page_Login() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(xpath = "//div[@id='b6-Button']//button")
	private WebElement button_Login;

	@FindBy(xpath = "//input[@id='Input_PasswordVal']")
	private WebElement input_Password;

	@FindBy(xpath = "//input[@id='Input_UsernameVal']")
	private WebElement input_Username;

	@FindBy(xpath = "//div[@class = 'feedback-message-text']")
	private WebElement label_FeedbackMessage;

	// ==================================================Return_Page_Objects==================================================
	public WebElement getButton_Login() {
		return button_Login;
	}
	
	public WebElement getInput_Password() {
		return input_Password;
	}
	
	public WebElement getInput_Username() {
		return input_Username;
	}
	
	public WebElement getLabel_FeedbackMessage() {
		return label_FeedbackMessage;
	}
}