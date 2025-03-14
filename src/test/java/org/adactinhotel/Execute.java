package org.adactinhotel;

import org.apache.commons.lang3.Validate;
import org.baseclass.SeleniumBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pageclass.LoginPageAdactin;
import org.pageclass.ValidatePageAdactin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Execute extends SeleniumBaseClass {

	public static SeleniumBaseClass seleniumBaseClass = new SeleniumBaseClass();
	public static ValidatePageAdactin validate = new ValidatePageAdactin();
	private WebElement click;
	public LoginPageAdactin loginPage1;

	@Given("Browserlaunch and loginpage")
	public void browserlaunch_and_loginpage() {
		try {

			seleniumBaseClass.Driverinitialization("chrome");
			seleniumBaseClass.launchUrl("https://adactinhotelapp.com/index.php");

			WebElement username = seleniumBaseClass.driver.findElement(By.id("username"));
			seleniumBaseClass.sendKeysByJava(username, "venkatrs");

			WebElement pass = seleniumBaseClass.driver.findElement(By.id("password"));
			seleniumBaseClass.sendKeysByJava(pass, "venkirs");

			seleniumBaseClass.screenCapture("login");
			WebElement login = seleniumBaseClass.driver.findElement(By.id("login"));
			seleniumBaseClass.clickToLogin(login);
			seleniumBaseClass.screenCapture("bookingpage");
			validate = new ValidatePageAdactin();
			if (validate.getPage().isDisplayed()) {
				
				System.out.println("login page validate successfully");
			}

		} catch (

		Exception e) {

		}

	}

	@When("User should be in room details")
	public void user_should_be_in_room_details() {
		try {
			seleniumBaseClass.screenCapture("logged in");
			validate = new ValidatePageAdactin();
			if (validate.getAfterlogin().isDisplayed()) {

				System.out.println("after login page validate successfully");

			}

			WebElement locate = driver.findElement(By.xpath("//select[@name='location']"));
			seleniumBaseClass.selectLocation(locate, "Sydney");

			WebElement hotel = driver.findElement(By.xpath("//select[@name='hotels']"));
			seleniumBaseClass.selectLocation(hotel, "Hotel Hervey");

			WebElement room = driver.findElement(By.xpath("//select[@name='room_type']"));
			seleniumBaseClass.selectLocation(room, "Deluxe");
			seleniumBaseClass.screenCapture("bookingpage1");

			WebElement number = driver.findElement(By.xpath("//select[@name='room_nos']"));
			seleniumBaseClass.selectRoomnumber(number, 1);

			WebElement checkin = driver.findElement(By.xpath("//input[@name='datepick_in']"));
			seleniumBaseClass.chechIn(checkin, "30/01/2025");

			WebElement checkout = driver.findElement(By.xpath("//input[@name='datepick_out']"));
			seleniumBaseClass.chechOut(checkout, "31/01/2025");
			seleniumBaseClass.screenCapture("dataupdate");

			WebElement adult = driver.findElement(By.name("adult_room"));
			seleniumBaseClass.adultforroom(adult, 2);

			WebElement child = driver.findElement(By.xpath("//select[@name='child_room']"));
			seleniumBaseClass.childrenforroom(child, 2);
			Thread.sleep(5000);

			WebElement search = driver.findElement(By.name("Submit"));
			seleniumBaseClass.clickSearch(search);
			seleniumBaseClass.screenCapture("search");
			validate = new ValidatePageAdactin();
			WebElement radio = driver.findElement(By.id("radiobutton_0"));
			seleniumBaseClass.clickradioButton(radio);

			WebElement cont = driver.findElement(By.id("continue"));
			seleniumBaseClass.clickcontinueButton(cont);

		} catch (Exception e) {
			validate = new ValidatePageAdactin();
			seleniumBaseClass.screenCapture("hotel search");
			if (validate.getAfterlogin().isDisplayed()) {

				System.out.println("select hotel validation successful");
			}
		}
	}

	@When("User should enter personal details")
	public void user_should_enter_personal_details() {
		try {
			WebElement text1 = driver.findElement(By.xpath("//td[text()='Welcome to Adactin Group of Hotels']"));
			String text2 = seleniumBaseClass.getText(text1);
			System.out.println(text2);

			WebElement firstname1 = driver.findElement(By.name("first_name"));
			seleniumBaseClass.firstName(firstname1, "venkatesh");

			WebElement lastname1 = driver.findElement(By.name("last_name"));
			seleniumBaseClass.lastName(lastname1, "somu");

			WebElement add = driver.findElement(By.name("address"));
			seleniumBaseClass.address(add, "salem");

			WebElement cardnumber = driver.findElement(By.name("cc_num"));
			seleniumBaseClass.sendKeysByJava(cardnumber, "1234567890987654");

			WebElement type = driver.findElement(By.id("cc_type"));
			seleniumBaseClass.cardtype(type, "VISA");

			WebElement expmonth = driver.findElement(By.name("cc_exp_month"));
			seleniumBaseClass.expiryMonth(expmonth, 3);

			WebElement expyear = driver.findElement(By.id("cc_exp_year"));
			seleniumBaseClass.expiryYear(expyear, "2026");

			WebElement cvvnum = driver.findElement(By.name("cc_cvv"));
			seleniumBaseClass.cvvNumber(cvvnum, "089");

			seleniumBaseClass.scrolldown();

			WebElement booknow = driver.findElement(By.name("book_now"));
			seleniumBaseClass.book(booknow);
			Thread.sleep(10000);

			seleniumBaseClass.screenCapture("book");

			WebElement myliti = driver.findElement(By.xpath("//input[@name='my_itinerary']"));
			seleniumBaseClass.myitinerary(myliti);
			System.out.println("book hotel validation successful");

		} catch (Exception e) {
		}
	}

	@When("User should cancel booking")
	public void user_should_cancel_booking() {
		try {
			WebElement checkboxclick = driver.findElement(By.id("check_all"));
			seleniumBaseClass.cancelItinerary(checkboxclick);

			WebElement cancelall = driver.findElement(By.name("cancelall"));
			seleniumBaseClass.cancelAll(cancelall);
			seleniumBaseClass.alertpopup(click);

		} catch (Exception e) {
		}

	}

	@Then("Adactin hotel again loginpage")
	public void adactin_hotel_again_loginpage() {
		try {
			WebElement logout = driver.findElement(By.id("logout"));
			seleniumBaseClass.logoutbutton(logout);

			WebElement loginclick = driver.findElement(By.xpath("//a[@href='index.php']"));
			Thread.sleep(3000);

			seleniumBaseClass.clickToLogin(loginclick);
			seleniumBaseClass.screenCapture("nextloginpage");

		} catch (Exception e) {
		}
	
	 System.out.println("my name is venkat");
	}
	
}
