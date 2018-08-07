package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Topi_06_User_Interactions {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

// @Test
  public void TC_01() throws Exception {
	  driver.get("http://daominhdam.890m.com/");
	  
	  WebElement Hovertext = driver.findElement(By.xpath("//a[@data-toggle='tooltip']"));
	  
	  Actions action = new Actions(driver);
	  
	  action.moveToElement(Hovertext).perform();
	  Thread.sleep(3000);
	  
	  //verify tooltip
	  Assert.assertEquals( driver.findElement(By.xpath("//div[@Role='tooltip']//div[@class= 'tooltip-inner']")).getText(), "Hooray!");
	  
  }
  
  @Test
  public void TC_01_Case02() throws Exception {
	  driver.get("https://www.myntra.com/");
	  
	  WebElement Hovertext = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
	  
	  Actions action = new Actions(driver);
	  
	  action.moveToElement(Hovertext).perform();
	  Thread.sleep(3000);
	  
	  //action.click(driver.findElement(By.xpath("//div[@class='desktop-userActions']//a[@data-track='login']"))).perform();
	  
	  clickElementByJavascript("//div[@class='desktop-userActions']//a[@data-track='login']");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

  }
  
  //@Test
  public void TC_02() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> selecttable = driver.findElements(By.xpath("//li[@class ='ui-state-default ui-selectee']"));
	  
	  Actions action = new Actions(driver);
	  
	  //Chon tu 1->4
	  //action.clickAndHold(selecttable.get(0)).moveToElement(selecttable.get(3)).release().perform();
	  
	  //Chon 1-6-7-12
	  
	  //Gia lap nhan phim ctrl xuong
	  action.keyDown(Keys.CONTROL).build().perform();
	  selecttable.get(0).click();
	  selecttable.get(5).click();
	  selecttable.get(6).click();
	  selecttable.get(11).click();
	  
	//Gia lap nha phim ctrl ra
	  action.keyUp(Keys.CONTROL).build().perform();
	  
	  List <WebElement> Result = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals( Result.size(),4);
	  
	  
	  
  }
  
  //@Test
  public void TC_03() throws Exception {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  
	  WebElement Doubleclick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  
	  Actions action = new Actions(driver);
	  
	  action.doubleClick(Doubleclick).perform();
	  Thread.sleep(5000);
	  
	  Alert alert = driver.switchTo().alert();
	  
	  Assert.assertEquals( alert.getText(),"The Button was double-clicked.");
  }
  
  //@Test
  public void TC_04() throws Exception {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  
	  WebElement Rightclick = driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action = new Actions(driver);
	  
	  action.contextClick(Rightclick).perform();
	  
	  //hover
	  WebElement Hovertext = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
	  action.moveToElement(Hovertext).perform();
	  
	  //verify hover successfully
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and  contains(@class,'context-menu-hover')]")).isDisplayed());
 
	  //Click Quit
	  action.click(Hovertext).perform();
	  
	  //accept alert
//	  Alert alert = driver.switchTo().alert();
//	  alert.accept();
	  
  }

  //@Test
  public void TC_05() throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
	  
	  WebElement Source = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement Taget = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  
	  Actions action = new Actions(driver);
	  
	  action.dragAndDrop(Source, Taget).perform();
	  
	  Assert.assertEquals( Taget.getText(),"You did great!");
	  
  }
  //@Test
  public void TC_05_html() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	  
	  WebElement Source = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement Taget = driver.findElement(By.xpath("//div[@id='droppable']"));
	  
	  Actions action = new Actions(driver);
	  
	  action.dragAndDrop(Source, Taget).perform();
	  
	  Assert.assertEquals( Taget.getText(),"Dropped!");
	  
	  
	  
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
