package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Base;

public class Page_AddEmployee extends Base {
	// ==================================================Initialize_Page_Objects==================================================
	public Page_AddEmployee() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(xpath = "//button[@id='Save']")
	private WebElement button_Save;

	@FindBy(xpath = "//input[@id='Input_Age']")
	private WebElement input_Age;

	@FindBy(xpath = "//input[@id='Input_FirstName']")
	private WebElement input_FirstName;

	@FindBy(xpath = "//input[@id='Input_LastName']")
	private WebElement input_LastName;

	@FindBy(xpath = "//span[@id='NewDetail_Title']")
	private WebElement label_NewDetailsPageTitle;

	// ==================================================Return_Page_Objects==================================================
	public WebElement getButton_Save() {
		return button_Save;
	}

	public WebElement getInput_Age() {
		return input_Age;
	}

	public WebElement getInput_FirstName() {
		return input_FirstName;
	}

	public WebElement getInput_LastName() {
		return input_LastName;
	}

	public WebElement getLabel_NewDetailsPageTitle() {
		return label_NewDetailsPageTitle;
	}
}