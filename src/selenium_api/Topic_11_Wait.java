package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_11_Wait {
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
	driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

	driver.findElement(By.xpath("//div[@id='start']/button")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
	String resulttext = driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).getText();
	
	Assert.assertEquals(resulttext, "Hello World!");
	
  }
  //@Test
  public void TC_02() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  
	  //Step 02 - Wait cho "Date Time Picker" được hiển thị
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
	  
	  //In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  String Notselected = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span")).getText();
	  System.out.println(Notselected);
	  
	  //verify
	  Assert.assertEquals(Notselected, "No Selected Dates to display.");
	  
	  //Chọn ngày hiện tại 
	  driver.findElement(By.xpath("//td//a[text()='5']")).click();
	  
	  //Wait cho đến khi "loader ajax" không còn visible
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".raDiv")));
	  
	  //Wait cho selected date = 5 được visible
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='5']")));
	  
	  //Verify ngày đã chọn bằng 5
	  String SelectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'September 05')]")).getText();
	  
	  Assert.assertEquals(SelectedDate, "Wednesday, September 05, 2018");
  
  }
  
  @Test
  public void TC_03() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  
	  WebElement countdount = driver.findElement(By.xpath(".//*[@id='javascript_countdown_time']"));
  
	  wait.until(ExpectedConditions.visibilityOf(countdount));

	// Khởi tạo Fluent wait
	new FluentWait<WebElement>(countdount)
	           // Tổng time wait là 15s
	           .withTimeout(15, TimeUnit.SECONDS)
	            // Tần số mỗi 1s check 1 lần
	            .pollingEvery(1, TimeUnit.SECONDS)
	           // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	            .ignoring(NoSuchElementException.class)
	            // Kiểm tra điều kiện
	            .until(new Function<WebElement, Boolean>() {
	                public Boolean apply(WebElement element) {
	                           // Kiểm tra điều kiện countdount = 00
	                           boolean flag =  element.getText().endsWith("00");
	                           System.out.println("Time = " +  element.getText());
	                           // return giá trị cho function apply
	                           return flag;
	                      }
	               });
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
