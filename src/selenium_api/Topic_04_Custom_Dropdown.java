package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_04_Custom_Dropdown {
	WebDriver driver;  
	WebDriverWait wait;
 
  @BeforeClass
  public void beforeClass() {
	  //driver = new FirefoxDriver();
	  System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  
	  wait = new  WebDriverWait(driver,30);
	  
  }
  
  //@Test
  public void TC_02_CustomDropdown() throws Exception {	  
	  
	//Jquery
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

	  //Angular
	  driver.get("https://material.angular.io/components/select/examples");
	  selectDropdown("//mat-select[@placeholder='State']","//mat-option/span", "Virginia" );
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text() = 'Virginia']")).isDisplayed());

	  //Kendo-ui	  
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index ");
	  selectDropdown("//span[@aria-labelledby='color_label']","//ul[@id='color_listbox']/li", "Grey");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-labelledby='color_label']//span[@class='k-input' and text() = 'Grey' ]/parent::span")).isDisplayed());

	  //Vuejs
	  driver.get(" https://mikerodham.github.io/vue-dropdowns/");
	  selectDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//li", "Third Option");
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'dropdown-toggle'  and contains(text(), 'Third Option')]")).isDisplayed());
	
	  //editable
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");
	  selectDropdown("//div[@id=\"default-place\"]//input[@class=\"form-control es-input\"]","//div[@id='default-place']//li", "Jeep");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//input[@class='form-control es-input' and //ul//li[@class='es-visible' and text() = 'Jeep'] ] ")).isDisplayed());
	  
	  Thread.sleep(3000);
  }
  
 // @Test
  public void TC_03_AdvanceCustomDropdown() throws Exception {	  
	  driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/");
	  selectDropdown("//p[@id='e1_t']//button[@class='ms-choice']","//p[@id='e1_t']//div[@class='ms-drop bottom']//span", "February");
	  Thread.sleep(3000);
	  Assert.assertTrue(driver.findElement(By.xpath("//button[@class='ms-choice']//span[contains(text(),'February')]")).isDisplayed());
	 
  }
  @Test
  public void TC_04_MultiSelect() throws Exception{	  
	  
	  driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/");
	  WebElement multiselect = driver.findElement(By.xpath("//p[@id='e1_f']//select[@class='w300']")); 
	  Select sel = new Select(multiselect);
	  
	  sel.selectByValue("1");
	  sel.selectByValue("2");
	  sel.selectByValue("3");
	  sel.selectByValue("4");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='e1_f']//select[@class='w300']//option[@value='1']")).isSelected());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='e1_f']//select[@class='w300']//option[@value='2']")).isSelected());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='e1_f']//select[@class='w300']//option[@value='3']")).isSelected());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='e1_f']//select[@class='w300']//option[@value='4']")).isSelected());
	  Thread.sleep(3000);
	 
  }
  
 
  public void selectDropdown (String dropdown, String listitems, String value) throws Exception {
	  //Click vào dropdown
	 WebElement dropdownlist = driver.findElement(By.xpath(dropdown));
	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", dropdownlist);
	 dropdownlist.click();
	 Thread.sleep(3000);
	 
	  //Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
	  List <WebElement> allitems = driver.findElements(By.xpath(listitems));
	  
	  //Wait để tất cả phần tử trong dropdown được hiển thị
	  wait.until(ExpectedConditions.visibilityOfAllElements(allitems));
	  
	  //Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
	  for (WebElement item : allitems) {
		  if (item.getText().trim().equals(value)) {
			  //scroll
			  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", item);

			  item.click();
			  break;
		  }
		 
	  }
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
