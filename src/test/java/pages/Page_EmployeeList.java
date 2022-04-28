package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Base;

public class Page_EmployeeList extends Base {
	// ==================================================Initialize_Page_Objects==================================================
	public Page_EmployeeList() {
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(xpath = "//button[@id='AddEmployee']")
	private WebElement button_AddEmployee;

	@FindBy(xpath = "//input[@id='Input_TextVar']")
	private WebElement button_SearchEmployee;

	@FindBy(xpath = "//h1[@id = 'EmployeeList_Title']")
	private WebElement label_EmployeeListPageTitle;

	@FindBy(xpath = "//div[@id='IsTableLoadingOrEmpty']//*[text()='No items to show...']")
	private WebElement tableEmployeeList_Label_NoItemsToShow;

	@FindBy(xpath = "//tr[@class='table-row'][1]//td[@data-header='Age']")
	private WebElement tableEmployeeList_RowData_First_Age;

	@FindBy(xpath = "//tr[@class='table-row'][1]//td[@data-header='First Name']//a")
	private WebElement tableEmployeeList_RowData_First_FirstName;

	@FindBy(xpath = "//tr[@class='table-row'][1]//td[@data-header='Last Name']")
	private WebElement tableEmployeeList_RowData_First_LastName;

	// ==================================================Return_Page_Objects==================================================
	public WebElement getButton_AddEmployee() {
		return button_AddEmployee;
	}
	
	public WebElement getButton_SearchEmployee() {
		return button_SearchEmployee;
	}
	
	public WebElement getLabel_EmployeeListPageTitle() {
		return label_EmployeeListPageTitle;
	}
	
	public WebElement getTableEmployeeList_Label_NoItemsToShow() {
		return tableEmployeeList_Label_NoItemsToShow;
	}
	
	public WebElement getTableEmployeeList_RowData_First_Age() {
		return tableEmployeeList_RowData_First_Age;
	}
	
	public WebElement getTableEmployeeList_RowData_First_FirstName() {
		return tableEmployeeList_RowData_First_FirstName;
	}
	
	public WebElement getTableEmployeeList_RowData_First_LastName() {
		return tableEmployeeList_RowData_First_LastName;
	}
}