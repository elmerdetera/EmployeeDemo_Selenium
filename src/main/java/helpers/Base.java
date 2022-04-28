package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver webDriver;
	public static Properties properties;
	// ==================================================JIRA_Variables==================================================
	public static String jiraID;
	public static String jiraSummary;
	public static String jiraDescription = "Steps to Replicate:";

	public Base() {
		try {
			properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(
					"src\\test\\resources\\configurations\\Configuration.properties");

			properties.load(fileInputStream);
		} catch (IOException e) {

		}
	}

	public static void initialization() {
		String strBrowser = properties.getProperty("browser");
		Dimension dimension = new Dimension(Integer.parseInt(properties.getProperty("width")),
				Integer.parseInt(properties.getProperty("height")));

		if (strBrowser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.addArguments("--incognito");
			chromeOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			chromeOptions.addArguments("--ignore-ssl-errors=yes");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--headless"); // Disable this if you want to show browser.

			webDriver = new ChromeDriver(chromeOptions);
		} else if (strBrowser.equals("ie")) { // Need to put TabProcGrowth with value 0 in
												// HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\Main in
												// Registry.
			WebDriverManager.iedriver().setup();

			InternetExplorerOptions ieo = new InternetExplorerOptions();

			ieo.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			ieo.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			ieo.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieo.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

			webDriver = new InternetExplorerDriver(ieo);
		}

		// Use maximize or setSize separately.
		// webDriver.manage().window().maximize();
		webDriver.manage().window().setSize(dimension);
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		webDriver.get(properties.getProperty("url"));

		if (strBrowser.equals("ie")) {
			webDriver.get("javascript:document.getElementById('overridelink').click();");
		}
	}

	// ==================================================Do_Functions==================================================
	public static void actionElementClick(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			Actions actions = new Actions(webDriver);

			actions.click(webElement).build().perform();
		}
	}

	public static void actionSendKeys(String strKeys) {
		Actions actions = new Actions(webDriver);

		actions.sendKeys(strKeys).build().perform();
	}

	public static void alertAccept() {
		webDriver.switchTo().alert().accept();
	}

	public static void alertDismiss() {
		webDriver.switchTo().alert().dismiss();
	}

	public static void alertSendKeys(String strKeys) {
		webDriver.switchTo().alert().sendKeys(strKeys);
	}

	public static void launchURL(String strURL) {
		webDriver.get(strURL);
	}

	public static void navigateBack() {
		webDriver.navigate().back();
	}

	public static void navigateForward() {
		webDriver.navigate().forward();
	}

	public static void navigateToUrl(String strURL) {
		webDriver.navigate().to(strURL);
	}

	public static void refreshWebPage() {
		webDriver.navigate().refresh();
	}

	public static void scrollToBottom() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

		javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToCoordinates(int intWidth, int intHeight) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

		javascriptExecutor.executeScript("window.scrollBy(" + intWidth + "," + intHeight + ")");
	}

	public static void scrollToTop() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

		javascriptExecutor.executeScript("window.scrollTo(0, 0)");
	}

	public static void scrollToWebElement(WebElement webElement) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void switchFrameByWebElement(WebElement webElement) throws Exception {
		FluentWait fluentWait = new FluentWait(webDriver);

		fluentWait.withTimeout(Duration.ofSeconds(60));
		fluentWait.pollingEvery(Duration.ofSeconds(5));
		fluentWait.ignoring(NoSuchElementException.class);

		Integer intNumberOfFrames = webDriver.findElements(By.tagName("iframe")).size();

		for (int i = 0; i <= intNumberOfFrames; i++) {
			webDriver.switchTo().frame(i);
			fluentWait.until(ExpectedConditions.visibilityOf(webElement));

			if (isWebElementDisplayed(webElement)) {
				break;
			}
		}
	}

	public static void switchToParentFrame() {
		webDriver.switchTo().parentFrame();
	}

	public static void webElementClear(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			webElement.clear();
		}
	}

	public static void webElementClick(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			webElement.click();
		}
	}

	public static void webElementDoubleClick(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			Actions actions = new Actions(webDriver);

			actions.doubleClick(webElement).build().perform();
		}
	}

	public static void webElementDragAndDrop(WebElement sourceWebElement, WebElement targetWebElement)
			throws Exception {
		waitUntilWebElementVisible(sourceWebElement);
		waitUntilWebElementVisible(targetWebElement);

		if ((isWebElementDisplayed(sourceWebElement) && isWebElementEnabled(sourceWebElement))
				&& (isWebElementDisplayed(targetWebElement) && isWebElementEnabled(targetWebElement))) {
			Actions actions = new Actions(webDriver);

			actions.dragAndDrop(sourceWebElement, targetWebElement).build().perform();
		}
	}

	public static void webElementDropdownSelect(WebElement webElement, String strOption, String strValue)
			throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			Select select = new Select(webElement);

			if (strOption.equals("value")) {
				select.selectByValue(strValue);
			} else if (strOption.equals("index")) {
				select.selectByIndex(Integer.parseInt(strValue));
			} else if (strOption.equals("visibletext")) {
				select.selectByVisibleText(strValue);
			}
		}
	}

	public static void webElementMouseHover(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			Actions actions = new Actions(webDriver);

			actions.moveToElement(webElement).build().perform();
		}
	}

	public static void webElementRightClick(WebElement webElement) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			Actions actions = new Actions(webDriver);

			actions.contextClick(webElement).build().perform();
		}
	}

	public static void webElementSendKeys(WebElement webElement, String strKeys) throws Exception {
		waitUntilWebElementVisible(webElement);

		if (isWebElementDisplayed(webElement) && isWebElementEnabled(webElement)) {
			// webElement.sendKeys(strKeys);

			for (int i = 0; i < strKeys.length(); i++) {
				webElement.sendKeys(strKeys.substring(i, i + 1));
				// wait(50, false);
			}
		}
	}

	public static String convertColorToHexadecimal(String strColor) {
		String strHex = Color.fromString(strColor).asHex();

		return strHex;
	}

	public static String takeScreenshot(String strFileName) throws Exception {
		String strFilePath = "";
		TakesScreenshot screenshot = (TakesScreenshot) webDriver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		strFilePath = "reports\\screenshots\\" + strFileName + ".png";

		FileUtils.copyFile(file, new File(strFilePath));

		return strFilePath;
	}

	// ==================================================See_Functions==================================================
	public static boolean checkWebElementDropdownOptions(WebElement webElement, String[] strExpectedOptions) { // expectedOptions
																												// should
																												// be
																												// like
																												// this
																												// ->
																												// {"Select",
																												// "Test1",
																												// "Test2"}
		int intOptionsFound = 0;
		Select select = new Select(webElement);
		List<WebElement> options = select.getOptions();

		for (WebElement we : options) {
			for (int i = 0; i < strExpectedOptions.length; i++) {
				if (we.getText().equals(strExpectedOptions[i])) {
					intOptionsFound++;
				}
			}
		}
		if (intOptionsFound == strExpectedOptions.length) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkWebElementListSorting(List<WebElement> webElementList, Boolean blnCheckDescending) {
		ArrayList<String> listObtained = new ArrayList<String>();
		ArrayList<String> listSorted = new ArrayList<String>();

		for (WebElement webElement : webElementList) {
			listObtained.add(webElement.getText());
		}

		for (String str : listObtained) {
			listSorted.add(str);
		}

		Collections.sort(listSorted);

		if (blnCheckDescending) {
			Collections.reverse(listSorted);
		}

		if (listSorted.equals(listObtained)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAlertPresent() {
		try {
			webDriver.switchTo().alert();

			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static boolean isWebElementDisplayed(WebElement webElement) throws Exception {
		try {
			return webElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isWebElementEnabled(WebElement webElement) throws Exception {
		try {
			return webElement.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isWebElementFocused(WebElement webElement) throws Exception {
		try {
			return webElement.equals(webDriver.switchTo().activeElement());
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isWebElementSelected(WebElement webElement) throws Exception {
		try {
			return webElement.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public static String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	public static String getPageTitle() {
		return webDriver.getTitle();
	}

	public static String getPseudoElementValue(String strSelector, String strPseudoElement, String strCSSProperty) { // Example
																														// is
																														// strSelector
																														// =
																														// label.MandatoryLabel,
																														// strPseudoElement
																														// =
																														// :after,
																														// strCSSProperty
																														// =
																														// content.
		String strScript = "return window.getComputedStyle(document.querySelector('" + strSelector + "'),'"
				+ strPseudoElement + "').getPropertyValue('" + strCSSProperty + "')";
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		String strContent = (String) js.executeScript(strScript);

		return strContent;
	}

	public static String getStringDateTimeNow(String strFormat) { // Example is "_MMddyy_HHmm".
		String strFormattedDateTimeNow = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
		strFormattedDateTimeNow = simpleDateFormat.format(calendar.getTime());

		return strFormattedDateTimeNow;
	}

	public static String getWebElementAttributeValue(WebElement webElement, String strAttribute) {
		return webElement.getAttribute(strAttribute).toString();
	}

	public static String getWebElementCssValue(WebElement webElement, String strCSS) {
		return webElement.getCssValue(strCSS).toString();
	}

	public static String getWebElementDropdownSelectedValue(WebElement webElement) {
		Select select = new Select(webElement);
		WebElement webElementSelectedValue = select.getFirstSelectedOption();

		return webElementSelectedValue.getText();
	}

	public static String getWebElementText(WebElement webElement) {
		return webElement.getText();
	}

	public static String getWebElementXPath(WebElement webElement) {
		return webElement.toString().substring(webElement.toString().indexOf("/"),
				webElement.toString().lastIndexOf("'"));
	}

	// ==================================================Wait_Functions==================================================
	public static void wait(Integer intDuration, Boolean blnIsMinutes) throws Exception {
		if (blnIsMinutes) {
			intDuration = intDuration * 60000;
		}

		Thread.sleep(intDuration);
	}

	public static void waitUntilAlertPresent() {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilTextPresentInWebElement(WebElement webElement, String strText) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.textToBePresentInElement(webElement, strText));
	}

	public static void waitUntilWebBrowserTitleContains(String strTitle) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.titleContains(strTitle));
	}

	public static void waitUntilWebElementAttributeContains(WebElement webElement, String strAttribute,
			String strValue) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.attributeContains(webElement, strAttribute, strValue));
	}

	public static void waitUntilWebElementAttributeDoesNotContains(WebElement webElement, String strAttribute,
			String strValue) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(
				ExpectedConditions.not(ExpectedConditions.attributeContains(webElement, strAttribute, strValue)));
	}

	public static void waitUntilWebElementClickable(WebElement webElement) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public static void waitUntilWebElementInvisible(WebElement webElement) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
	}

	public static void waitUntilWebElementXPathNotExisting(String strWebElementXPath) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strWebElementXPath)));
	}

	public static void waitUntilWebElementSelectionStateToBe(WebElement webElement, Boolean blnIsSelected) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(webElement, blnIsSelected));
	}

	public static void waitUntilWebElementVisible(WebElement webElement) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

		webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
	}
}