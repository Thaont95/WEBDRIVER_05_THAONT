package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_TextArea_Dropdown {
	WebDriver driver;
	
	By nameTextBox = By.xpath("//input[@name='name']");
	By dob = By.xpath("//input[@name='dob']");
	By address = By.xpath("//textarea[@name='addr']");
	By city = By.xpath("//input[@name='city']");
	By State = By.xpath("//input[@name='state']");
	By Pin = By.xpath("//input[@name='pinno']");
	By Mobilenumber = By.xpath("//input[@name='telephoneno']");
	By Email = By.xpath("//input[@name='emailid']");
	By Password = By.xpath("//input[@name='password']");

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  
	  
  }
  
  @Test
  public void TC_01_Dropdown() throws Exception {
	  
	  driver.get("http://daominhdam.890m.com/");
	  
	  Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  
	  Assert.assertTrue(!jobRole.isMultiple());
	  
	  jobRole.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Automation Tester");
	  
	  jobRole.selectByValue("manual");
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  jobRole.selectByIndex(3);
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  jobRole.getOptions().size();
	  Assert.assertEquals(jobRole.getOptions().size(), 5);
	  
	  Thread.sleep(5000);
  }

  @Test
  public void TC_02_TextBox_TextArea() throws InterruptedException {
	  driver.get("http://demo.guru99.com/v4/");
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145484");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ydEzatA");
	  
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();	  
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[@behavior='alternate']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
	  
	  driver.findElement(nameTextBox).sendKeys("Automation test");
	  driver.findElement(dob).sendKeys("1995-12-30");
	  driver.findElement(address).sendKeys("15 Pham Hung");
	  driver.findElement(city).sendKeys("Ha Noi");
	  driver.findElement(State).sendKeys("Nam Tu Liem");
	  driver.findElement(Pin).sendKeys("123456");
	  driver.findElement(Mobilenumber).sendKeys("1234567890");
	  driver.findElement(Email).sendKeys("Automation" + randomEmail() + "@gmail.com");
	  driver.findElement(Password).sendKeys("123456");
	  
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  //Get ra thông tin của Customer ID
	  
	  String CustomerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
	  
	  //Chọn menu Edit Customer > Nhập Customer ID > Submit
	  driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  
	  //Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
	  
	  Assert.assertEquals(driver.findElement(nameTextBox).getAttribute("value"),"Automation test" ); 
	  Assert.assertEquals(driver.findElement(address).getText(), "15 Pham Hung" );
	  
	  //Nhập giá trị mới tại 2 field Customer Address và City > Submit
	  
	  driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
	  driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("edit customer address");
	  driver.findElement(By.xpath("//input[@name='city']")).clear();
	  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("edit city");
	  
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  //Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi đã Edit thành công
	  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(), "edit customer address");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(), "edit city");

	  Thread.sleep(5000);
}
  
  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
	  
  }

}
