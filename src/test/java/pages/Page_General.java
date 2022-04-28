package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Base;

public class Page_General extends Base {
	// ==================================================Initialize_Page_Objects==================================================
	public Page_General() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(xpath = "//span[text() = 'Log out']/parent::a")
	private WebElement button_Logout;

	@FindBy(xpath = "//div[@data-block = 'Common.Menu']//div[@data-block = 'Common.ApplicationTitle']/div")
	private WebElement image_EmployeeLogo;

	@FindBy(xpath = "//div[@class='user-info']//span[@data-expression and text()]")
	private WebElement label_Username;

	// ==================================================Return_Page_Objects==================================================
	public WebElement getButton_Logout() {
		return button_Logout;
	}

	public WebElement getImage_EmployeeLogo() {
		return image_EmployeeLogo;
	}

	public WebElement getLabel_Username() {
		return label_Username;
	}
}