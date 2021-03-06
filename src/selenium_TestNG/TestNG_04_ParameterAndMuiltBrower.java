package selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestNG_04_ParameterAndMuiltBrower {
	WebDriver driver;
	
	@Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String Browsername) {

		  if (Browsername.equals("chrome")) {
			  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			  driver= new ChromeDriver();
			  
		  }else if (Browsername.equals("fire")){
			  System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			  driver= new InternetExplorerDriver();
		  }else {
			  driver = new FirefoxDriver();
		  }
		 
		  driver.get("http://live.guru99.com/index.php/customer/account/login/");
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	
  @Test
	  public void TC_LogintoSystem() {
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationtesting05@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		  driver.findElement(By.xpath("//button[@id='send2']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text() ='My Dashboard']")).isDisplayed());
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
