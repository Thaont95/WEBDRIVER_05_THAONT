package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Frame_iFrame {
	WebDriver driver;
	WebDriverWait wait;
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver,10);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void TC_01() {
	  driver.get("http://www.hdfcbank.com/");
	  
	  //Switch to iframe
	  List <WebElement> notifIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  
	  if (notifIframe.size()>0) {		 
		  driver.switchTo().frame(notifIframe.get(0));
		//check close popup display
		  clickElementByJavascript("//div[@id='div-close']");
		  //driver.findElement(By.xpath("//div[@id='div-close']")).click();
		  
		  //Switch to top wwindowns
		  driver.switchTo().defaultContent();
		  
	  }
	  // Verify đoạn text được hiển thị:  What are you looking for?
	  WebElement Lookingfor = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(Lookingfor);
	  
	  String checktext = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	  Assert.assertEquals(checktext,"What are you looking for?");
	  
	//Switch to top wwindowns
	  driver.switchTo().defaultContent();
	  
	  //Verify banner image được hiển thị
	 
	  WebElement imgbaner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(imgbaner);
	  
	  By Banner = By.xpath("//div[@id='productcontainer']//img");
	  
	  List <WebElement> listImg = driver.findElements(Banner);
	  int listImgSize = listImg.size();
	  
	  Assert.assertEquals(listImgSize,6);

	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Banner));

	  //Verify flipper banner được hiển thị và có 8 items
	  //Switch to top wwindowns
	  driver.switchTo().defaultContent();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
	  
	  List <WebElement> flipBannerImg = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  
	  int flipBannerImgNumber = flipBannerImg.size();
	  Assert.assertEquals(flipBannerImgNumber,8);	  
	  
	  for(WebElement img:flipBannerImg) {
		  Assert.assertTrue(img.isDisplayed());
		  System.out.println("image display");
		  
	  }
	  
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

}
