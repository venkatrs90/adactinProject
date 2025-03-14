package org.pageclass;


import org.baseclass.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidatePageAdactin extends SeleniumBaseClass {
	public ValidatePageAdactin() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[text()='Existing User Login - Build 1']")
	private WebElement page;

	public WebElement getPage() {
		return page;
	}

	@FindBy(xpath = "//td[text()='Search Hotel ']")
	private WebElement Afterlogin;

	public WebElement getAfterlogin() {
		return Afterlogin;
	}

	@FindBy(xpath = "//td[text()='Select Hotel ']")
	private WebElement hotelsel;

	public WebElement getHotelsel() {
		return hotelsel;
	}

	@FindBy(xpath = "//td[text()='Book A Hotel ']")
	private WebElement bookhotel;

	public WebElement getBookhotel() {
		return bookhotel;
	}
}
