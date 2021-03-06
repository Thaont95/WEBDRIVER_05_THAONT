package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XpathCss {
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS );
	  driver.manage().window().maximize();
	  driver.get("http://live.guru99.com/");
	  }
  
  @Test
  public void TC_01_CheckUrlAndTitle() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page");
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  
	  driver.navigate().back();	  
	 // driver.findElement(By.xpath("//a[@class = 'back-link']")).click();
	  String LoginPageUrl = driver.getCurrentUrl();
	  Assert.assertEquals(LoginPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
	  
	  driver.navigate().forward();
	  //driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  String CreateaccountPageUrl = driver.getCurrentUrl();
	  Assert.assertEquals(CreateaccountPageUrl, "http://live.guru99.com/index.php/customer/account/create/");
  }

  @Test
  public void TC_02_Login_Empty() {
	  /*
	      Step 01 - Truy cập vào trang: http://live.guru99.com/
		  Step 02 - Click vào link "My Account" để tới trang đăng nhập
		  Step 03 - Để trống Username/ Password
		  Step 04 - Click Login button
		  Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
		  
	  */
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//input[@id='email']"));
	  driver.findElement(By.xpath("//input[@id='pass']"));
	  driver.findElement(By.xpath("//button[@name='send']")).click();
	  
	  String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	  Assert.assertEquals("This is a required field.", emailErrorMsg);
	  
	  String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
	  Assert.assertEquals("This is a required field.", passErrorMsg);
	  
	  
	  
  }
  
  @Test
  
  public void TC_03_Invalid_Email() {
	  /*  
	      Step 01 - Truy cập vào trang: http://live.guru99.com/
		  Step 02 - Click vào link "My Account" để tới trang đăng nhập
		  Step 03 - Nhập email invalid: 123434234@12312.123123
		  Step 04 - Click Login button
		  Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	 */
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.xpath("//button[@name='send']")).click();
	  
	  String invalidemailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	  Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", invalidemailErrorMsg);
	  
	  
  }
  
  @Test
  public void TC_04_Password_Incorrect() {
	  /*
	      Step 01 - Truy cập vào trang: http://live.guru99.com/
		  Step 02 - Click vào link "My Account" để tới trang đăng nhập
		  Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
		  Step 04 - Click Login button
		  Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
		  
	*/
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
	  driver.findElement(By.xpath("//button[@name='send']")).click();
	  
	  String incorrectpassErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
	  Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", incorrectpassErrorMsg);
	  
  }
  
  @Test
  public void TC_05_Create_Account() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Nguyen");
	  driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Thao");
	  driver.findElement(By.id("email_address")).sendKeys("automation" + randomEmail() + "@gmail.com");
	  driver.findElement(By.id("password")).sendKeys("123456");
	  driver.findElement(By.id("confirmation")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@title = 'Register']")).click();
	  
	  String createaccountMsg = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering')]")).getText();
	  Assert.assertEquals("Thank you for registering with Main Website Store.", createaccountMsg );
	  
	  driver.findElement(By.xpath("//header[@id=\"header\"]//span[contains(text(),'Account')]")).click();
	  
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	  
	  driver.navigate().to("http://live.guru99.com/index.php/");
  
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  
  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
  }
	
}