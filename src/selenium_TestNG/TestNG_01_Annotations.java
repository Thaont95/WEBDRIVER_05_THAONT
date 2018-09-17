package selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestNG_01_Annotations {

	WebDriver driver;
	
	@BeforeClass
	  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  }
	
	@BeforeMethod
	  public void beforeMethod() {
		  System.out.println("before Method");
	  }
	
	@Test
	  public void TC_01_CheckURL() {
		String loginURL = driver.getCurrentUrl();
		Assert.assertEquals(loginURL, "http://live.guru99.com/index.php/customer/account/login/");
		
	}
	@Test
	  public void TC_02_CheckTitle() {
		String loginTitle = driver.getTitle();
		Assert.assertEquals(loginTitle, "Customer Login");
		
	}

  @AfterMethod
  public void afterMethod() {
	  
  }


  @AfterClass
  public void afterClass() {
	  driver.quit();
	  
  }



}
