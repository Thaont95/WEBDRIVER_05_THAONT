package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
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
  //@Test
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

 //@Test
  public void TC_02() {
	  driver.get("http://daominhdam.890m.com/");
	  clickElementByJavascript("//a[@target='_blank']");
	  
	  String parentWindow = driver.getWindowHandle();
	  System.out.println("Parent ID = " +parentWindow );
	  
	  switchToChildWindow(parentWindow);
	  
	  
//	  String homePageTitle = driver.getTitle();
//	  Assert.assertEquals(homePageTitle, "Google");
	  
	  String googletitle = driver.getTitle();
	  Assert.assertEquals(googletitle, "Google");
	  
	  closeAllWithoutParentWindows(parentWindow);
  }
  //@Test
  public void TC_02_1() {
	  driver.get("http://daominhdam.890m.com/");
	  clickElementByJavascript("//a[@target='_blank']");
	  
	  String parentWindow = driver.getWindowHandle();
	  System.out.println("Parent ID = " +parentWindow );
	  
	  //switchToChildWindow(parentWindow);
	  switchToChildWindowbytitle("Google");
	  
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Google");
	  
	  closeAllWithoutParentWindows(parentWindow);
  }
  
  @Test
  public void TC_03() {
	  driver.get("http://www.hdfcbank.com/");
	  clickElementByJavascript("//a[@target='_blank']");
	  
	  //Kiểm tra và close quảng cáo nếu có xuất hiện
	//Switch to iframe
	  List <WebElement> notifIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  
	  if (notifIframe.size()>0) {		 
		  driver.switchTo().frame(notifIframe.get(0));
		//check close popup display
		  clickElementByJavascript("//div[@id='div-close']");
		  
	  }
	  
	  //Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
	  clickElementByJavascript("//a[text()='Agri']");
	  
	  switchToChildWindowbytitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  //Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
	  clickElementByJavascript("//p[text()='Account Details']");
	  
	  switchToChildWindowbytitle("Welcome to HDFC Bank NetBanking");
	  
	  //Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	  //Switch to top windows
	  driver.switchTo().defaultContent();
	  
	  WebElement policyframe = driver.findElement(By.xpath("//frame[@name='footer']"));
	  
	  driver.switchTo().frame(policyframe);
	  clickElementByJavascript("//p[@class='footer']//a[text()='Privacy Policy']");
	  
	  switchToChildWindowbytitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  	  
	 //Click CSR link on Privacy Policy page
	  clickElementByJavascript("//a[text()='CSR']");
	  
	  //Back về Main window (Parent window)
	  String parentWindow = driver.getWindowHandle();
	  driver.switchTo().window(parentWindow);
	  
	  //Close tất cả popup khác - chỉ giữ lại parent window 
	  closeAllWithoutParentWindows(parentWindow);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.close();
  }
  
  //Only 2 Windows tab
  public void switchToChildWindow(String parent) {
	  //Get all Windows ID
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindow : allWindows) {
    	  System.out.println("Child ID = " +runWindow );
    	  //Kiem tra ID nao != parent thi switch qua
                  if (!runWindow.equals(parent)) {
                              driver.switchTo().window(runWindow);
                              break;
                  }
      }
  }
  
//>= 2 Windows tab
  public void switchToChildWindowbytitle(String title) {
	  //Get all Windows ID
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindow : allWindows) {
    	  System.out.println("Child ID = " +runWindow );
    	  
                              driver.switchTo().window(runWindow);
                              
                              //Get title cua page day ra
                              String currentWin = driver.getTitle();
                             

                              //Title cua current windows = title truyen vao
                              if (currentWin.equals(title)) {
                                  break;
                      }
      }
  }
  
  public void clickElementByJavascript(String locator) {
	  WebElement element=  driver.findElement(By.xpath(locator));
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}

//Close all windows without parent windows
  public boolean closeAllWithoutParentWindows(String parentWindow) {
	  
	  //Get all windows ID
      Set<String> allWindows = driver.getWindowHandles();
      
      //Duyet qua tung ID
      for (String runWindows : allWindows) {
    	  
    	  		//Kiem tra neu windowsID != parent ID
                  if (!runWindows.equals(parentWindow)) {
                	  			
                	  		//Switch qua windowsID
                              driver.switchTo().window(runWindows);
                              //Close
                              driver.close();
                  }
      }
      //Switch ve parentID
      driver.switchTo().window(parentWindow);
      
      //Kiem tra con duy nhat 1 ID cua parent
      if (driver.getWindowHandles().size() == 1)
                 return true;
      else
                 return false;
}
}
