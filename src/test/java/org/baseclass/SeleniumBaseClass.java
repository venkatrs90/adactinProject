package org.baseclass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumBaseClass {

	private static final String JavascriptExecutor = null;
	private static Workbook book;
	public static WebDriver driver;
	private JavascriptExecutor javascriptExecutor;

	public void Driverinitialization(String browsertype) {
		switch (browsertype) {

		case "chrome":

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("Invalid Browser Type");
			break;
		}
	}

	public void launchUrl(String Url) {
		driver.get(Url);

	}

	public static String getDataFromWorkbook(String sheetName, int rownum, int cellnum) {

		String value = null;

		try {
			File file = new File(
					"C:\\Users\\DELL\\eclipse-workspace_1\\FebMonthProjectAdactinCucumber12025\\Datatype\\inputdatas.xlsx");

			FileInputStream fileInputStream = new FileInputStream(file);
			Sheet sheet = book.getSheet(sheetName);
			Row row = sheet.getRow(rownum);
			Cell cell = row.getCell(cellnum);
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					value = simpleDateFormat.format(dateCellValue);

				} else {
					double numericCellValue = cell.getNumericCellValue();
					BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
					value = valueOf.toString();
					break;
				}
			default:
				break;
			}

		} catch (Exception e) {

		}

		return value;
	}

	public static String getDataFromProperties(String propertyKey) {

		String property = null;

		try {
			File file = new File("DateBase\\config.properties");
			FileReader fileReader = new FileReader(file);
			Properties properties = new Properties();
			properties.load(fileReader);
			property = properties.getProperty(propertyKey);

		} catch (Exception e) {

		}

		return property;

	}

	public void sendkeysByJSE(WebElement element, String keysToSend) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute( 'value' ,'" + keysToSend + "')", element);

	}

	public void sendKeysByJava(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void clickByJSE(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void screenCapture(String name) {

		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
			File target = new File("D:\\picture\\images\\screenshot\\" + name + ".png");
			FileUtils.copyFile(source, target);

		} catch (Exception e) {

		}

	}

	public String getText(WebElement element) {

		String text = element.getText();
		return text;

	}

	public String getWindowsID(int requiredWindowsIndexNumber) {

		String currentWindowsID = driver.getWindowHandle();
		Set<String> allWindowsID = driver.getWindowHandles();

		List<String> list = new LinkedList<String>();
		list.addAll(allWindowsID);

		String requiredWindowsID = list.get(requiredWindowsIndexNumber);

		return requiredWindowsID;
	}

	public void switchToWindows(String requiredWindowsID) {
		driver.switchTo().window(requiredWindowsID);

	}

	public void selectLocation(WebElement element, String string) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		int size = options.size();
		System.out.println("Number of location " + size);
		System.out.println("List of Location: ");
		for (WebElement a : options) {
			String text = a.getText();

			System.out.println(text);
		}
		s.selectByVisibleText(string);
	}

	public void selectHotel(WebElement element, int hotel) {
		Select s = new Select(element);
		List<WebElement> option1 = s.getOptions();
		int size = option1.size();
		System.out.println("Number of hotels " + size);
		System.out.println("List of Hotels ");
		for (WebElement b : option1) {
			String text = b.getText();

			System.out.println(text);
		}
		s.selectByIndex(3);

	}

	public void selectRoomType(WebElement element, String Type) {
		Select s = new Select(element);
		s.selectByVisibleText(Type);
	}

	public void selectRoomnumber(WebElement element, int n) {
		Select s = new Select(element);
		s.selectByIndex(n);
	}

	public void chechIn(WebElement element, String keysToSend) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute( 'value' ,'" + keysToSend + "')", element);
	}

	public void chechOut(WebElement element, String keysToSend) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute( 'value' ,'" + keysToSend + "')", element);
	}

	public void adultforroom(WebElement element, int n) {
		Select s = new Select(element);
		s.selectByIndex(n);
	}

	public void childrenforroom(WebElement element, int c) {
		Select s = new Select(element);
		s.selectByIndex(c);
	}

	public void clickSearch(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);
	}

	public void clickradioButton(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);
	}

	public void clickcontinueButton(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public String checking(WebElement element) {

		String text = element.getText();
		return text;
	}

	public void firstName(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void lastName(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void address(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void cardnum(WebElement element, long keysToSend) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute( 'value' ,'" + keysToSend + "')", element);
	}

	public void cardtype(WebElement element, String Type) {
		Select s = new Select(element);
		List<WebElement> option1 = s.getOptions();
		int size = option1.size();
		System.out.println("card Types " + size);
		for (WebElement b : option1) {
			String text = b.getText();

			System.out.println(text);
		}

		s.selectByVisibleText(Type);
	}

	public void expiryMonth(WebElement element, int month) {
		Select s = new Select(element);
		s.selectByIndex(month);
	}

	public void expiryYear(WebElement element, String year) {
		Select s = new Select(element);
		s.selectByValue(year);
	}

	public void cvvNumber(WebElement element, String keysToSend) {

		element.sendKeys(keysToSend);

	}

	public void book(WebElement element) {

		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public String bookingConform(WebElement element) {

		String text = element.getText();
		return text;
	}

	public void scrolldown() {
		try {
			Thread.sleep(5000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (Exception e) {

		}
	}

	public void myitinerary(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void cancelItinerary(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void cancelbutton(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void alert(String accept_cancel) {

		switch (accept_cancel) {

		case "accept":
			driver.switchTo().alert().accept();
			break;

		case "dismiss":
			driver.switchTo().alert().dismiss();
			break;

		default:
			break;
		}

	}

	public void logoutbutton(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void clickToLogin(WebElement element) {

		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void cancelselect(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);
	}

	public void cancelAll(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);
	}

	public void alertpopup(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);
	}
}
