package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Button_Radio_Checkbox_Alert {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
@Test
  public void TC_01() {
	  driver.get("http://live.guru99.com/");
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	  
	  clickElementByJavascript("//span[contains(text(),'Create an Account')]");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }

@Test
  public void TC_02() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  
	  String DualzoneCheckbox = "//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input";
	  
	  clickElementByJavascript(DualzoneCheckbox);
	  Assert.assertTrue(isElementSelected(DualzoneCheckbox));
	  
	  CheckUnSelected(DualzoneCheckbox);
	  Assert.assertFalse(isElementSelected(DualzoneCheckbox));
	  
  }
  
 // @Test
  public void TC_03() {
	  driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
	  
	  String Petrolradiobutton = "//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input";
	  clickElementByJavascript(Petrolradiobutton);
	  //Assert.assertTrue(isElementSelected(Petrolradiobutton));
	  
	  Selectagain(Petrolradiobutton);
	  Assert.assertTrue(isElementSelected(Petrolradiobutton));
	  
  }
  
  @Test
  public void TC_04() {
	  Selectalert("//button[contains(text(),'Click for JS Alert')]","I am a JS Alert");
	  
	  String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
	  Assert.assertEquals(result,"You clicked an alert successfully");
  }
  
@Test
  public void TC_05() {

	  Selectalert("//button[contains(text(),'Click for JS Confirm')]","Click for JS Confirm");
	  
	  String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
	  Assert.assertEquals(result,"You clicked: Cancel");
  }
  
@Test
  public void TC_06() {

	  Selectalert("//button[contains(text(),'Click for JS Prompt')]","I am a JS prompt");
	  String result = driver.findElement(By.xpath("//p[@id='result']")).getText();
	  Assert.assertEquals(result,"You entered: Automation testing");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  public void clickElementByJavascript(String locator) {
	  WebElement element=  driver.findElement(By.xpath(locator));
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
  
  public boolean isElementSelected(String locator) {
	  WebElement element=  driver.findElement(By.xpath(locator));
	  return element.isSelected();
  }
  
  public void CheckUnSelected(String locator) {
	  if (isElementSelected(locator)) {
		  clickElementByJavascript(locator);
	  }
  }
  
  public void Selectagain(String locator) {
	  if (!isElementSelected(locator)) {
		  clickElementByJavascript(locator);
	  }
  }
  
  public void Selectalert(String locator, String value) {
	  
	  driver.get("http://daominhdam.890m.com/");
	  
	  WebElement dropdownlist = driver.findElement(By.xpath(locator));
	  	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", dropdownlist);
	  	
	  clickElementByJavascript(locator);
	  
	  Alert alert = driver.switchTo().alert();
	  String Alerttext = alert.getText();
	  if(Alerttext.equals("I am a JS Alert")){
		  alert.accept();
	  }
	   else if(Alerttext.equals("I am a JS Confirm")){
		   alert.dismiss();
	  }else {
		  alert.sendKeys("Automation testing");
		  alert.accept();
	  }
  }
}
