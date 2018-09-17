package selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;

public class TestNG_03_DataProvider {
	WebDriver driver;
	 @BeforeMethod
	  public void beforeMethod() {
		 driver = new FirefoxDriver();
			driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  }
	
  @Test(dataProvider = "LogintoSystem")
  public void TC_LogintoSystem(String username, String password) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text() ='My Dashboard']")).isDisplayed());
  }
  @Test(dataProvider = "newCustomer")
  public void TC_newCustomer(String username, String password) {
	  
  }

  @DataProvider
  public Object[][] LogintoSystem() {
    return new Object[][] {
      new Object[] { "automationtesting05@gmail.com", "123456" },
      
    };
  }
    
    @DataProvider
    public Object[][] newCustomer() {
      return new Object[][] {
        new Object[] { "automationtesting05@gmail.com", "123456" },
        
      };
  }
 

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
