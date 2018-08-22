package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_09_Javascript_Executor {
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  //@Test
  public void TC_01() {
	  OpenURLbyJS("http://live.guru99.com/");
	  String HomepageDomain = (String) executeForBrowserElement("return document.domain");
	  Assert.assertEquals( HomepageDomain,"live.guru99.com");
	  
	  //get URL
	  String HomepageURL = (String) executeForBrowserElement("return document.URL");
	  Assert.assertEquals( HomepageURL,"http://live.guru99.com/");
	  
	  WebElement Mobile = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  clickElementByJavascript(Mobile);
	  
	  // Add product to cart
	  WebElement AddProductToCart = driver.findElement(By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
	  clickElementByJavascript(AddProductToCart);
	  
	  //Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. 
	  String SSaddMsg = (String) executeForBrowserElement(" return document.documentElement.innerText");
	  Assert.assertTrue(SSaddMsg.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  //Open PRIVACY POLICY page 
	  WebElement Privacylink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  clickElementByJavascript(Privacylink);
	  
	  String Privacytitle = (String) executeForBrowserElement("return document.title");
	  Assert.assertEquals( Privacytitle,"Privacy Policy");
	  
	  //Srcoll xuống cuối page
	  scrollToBottomPage();
	  
	  //Verify dữ liệu có hiển thị với chỉ 1 xpath: 
	  WebElement Wishlist = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertTrue(Wishlist.isDisplayed());
	  
	  OpenURLbyJS("http://demo.guru99.com/v4/");
	  String HomepageDomainotherpage = (String) executeForBrowserElement("return document.domain");
	  Assert.assertEquals( HomepageDomainotherpage,"demo.guru99.com");
  }
  
  @Test
  public void TC_02() {
	  String fName ="Automation", lName = "Testing";
	  OpenURLbyJS("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  String HomepageDomain = (String) executeForBrowserElement("return document.domain");
	  Assert.assertEquals( HomepageDomain,"www.w3schools.com");
	  
	  //Remove thuộc tính disabled của field Last name
	  WebElement NameIframe = driver.findElement(By.xpath("//div[@id='iframecontainer']//iframe"));
	  driver.switchTo().frame(NameIframe);
	  
	  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(fName);
	  
	  WebElement lastName = driver.findElement(By.xpath("//input[@name='lname']"));
	  removeAttributeInDOM(lastName,"disabled");
	  lastName.sendKeys(lName);
	  
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  //Verify
	  String NameMsg = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
	  Assert.assertEquals(NameMsg, "fname=Automation&lname=Testing ");
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public Object OpenURLbyJS (String URL) {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location='"+ URL + "'");
  }
  	public Object highlightElement(WebElement element) {
  		JavascriptExecutor js = (JavascriptExecutor) driver;
  		return js.executeScript("arguments[0].style.border='6px groove red'", element);
  	}
  
  	public Object executeForBrowserElement(String javascript) {

                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript(javascript);

  	}
  	
  	 public Object clickElementByJavascript(WebElement element) {
  		    JavascriptExecutor je = (JavascriptExecutor) driver;
  		  return je.executeScript("arguments[0].click();", element);
  		}
  	 
  	public void removeAttributeInDOM(WebElement element, String attribute) {

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                     js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);

  	}
  	
  	public Object scrollToBottomPage() {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

}

}
