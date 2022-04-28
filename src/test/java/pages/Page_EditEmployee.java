package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Base;

public class Page_EditEmployee extends Base {
	// ==================================================Initialize_Page_Objects==================================================
	public Page_EditEmployee() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(xpath = "//button[@id='Delete']")
	private WebElement button_Delete;

	@FindBy(xpath = "//button[@id='Save']")
	private WebElement button_Save;

	@FindBy(xpath = "//input[@id='Input_Age']")
	private WebElement input_Age;

	@FindBy(xpath = "//input[@id='Input_FirstName']")
	private WebElement input_FirstName;

	@FindBy(xpath = "//input[@id='Input_LastName']")
	private WebElement input_LastName;

	@FindBy(xpath = "//span[@id='EditDetail_Title']")
	private WebElement label_EditDetailPageTitle;

	@FindBy(xpath = "//div[@class='popup-dialog']//button[@id='ConfirmDelete']")
	private WebElement popupDeleteConfirmation_Button_Yes;

	@FindBy(xpath = "//div[@class='popup-dialog']//span")
	private WebElement popupDeleteConfirmation_Label_Message;

	// ==================================================Return_Page_Objects==================================================
	public WebElement getButton_Delete() {
		return button_Delete;
	}

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

	public WebElement getLabel_EditDetailPageTitle() {
		return label_EditDetailPageTitle;
	}

	public WebElement getPopupDeleteConfirmation_Button_Yes() {
		return popupDeleteConfirmation_Button_Yes;
	}

	public WebElement getPopupDeleteConfirmation_Label_Message() {
		return popupDeleteConfirmation_Label_Message;
	}
}