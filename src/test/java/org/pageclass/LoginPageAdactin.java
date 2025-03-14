package org.pageclass;


import org.baseclass.SeleniumBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageAdactin extends SeleniumBaseClass {
	public LoginPageAdactin() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	private WebElement username;

	public WebElement getUsername() {
		return username;
	}

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	public WebElement getPassword() {
		return password;
	}

	@FindBy(xpath = "//input[@id='login']")
	private WebElement loginButton;

	public WebElement getLoginButton() {
		return loginButton;
	}
}
