package pages;

import helpers.Base;

public class Pages extends Base {
	public Pages() {

	}

	// ==================================================Initialize_Pages==================================================
	private Page_AddEmployee page_AddEmployee;
	private Page_EditEmployee page_EditEmployee;
	private Page_EmployeeList page_EmployeeList;
	private Page_General page_General;
	private Page_Login page_Login;

	// ==================================================Return_Pages==================================================
	public Page_AddEmployee getPageAddEmployee() {
		return (page_AddEmployee == null) ? page_AddEmployee = new Page_AddEmployee() : page_AddEmployee;
	}

	public Page_EditEmployee getPageEditEmployee() {
		return (page_EditEmployee == null) ? page_EditEmployee = new Page_EditEmployee() : page_EditEmployee;
	}

	public Page_EmployeeList getPageEmployeeList() {
		return (page_EmployeeList == null) ? page_EmployeeList = new Page_EmployeeList() : page_EmployeeList;
	}

	public Page_General getPageGeneral() {
		return (page_General == null) ? page_General = new Page_General() : page_General;
	}

	public Page_Login getPageLogin() {
		return (page_Login == null) ? page_Login = new Page_Login() : page_Login;
	}
}