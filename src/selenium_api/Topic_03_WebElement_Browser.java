package selenium_api;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_WebElement_Browser {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  //driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS );
	  
  }
@Test
  public void TC_01_IsDisplay() throws Exception{
	  WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	  
	  Assert.assertTrue(IsControlDisplay(emailTextBox));
	  Assert.assertTrue(IsControlDisplay(ageRadioButton));
	  Assert.assertTrue(IsControlDisplay(educationTextArea));
	  
	  if(IsControlDisplay(emailTextBox) && IsControlDisplay(educationTextArea)){
		  emailTextBox.sendKeys("Automation Testing");
		  educationTextArea.sendKeys("Automation Testing");
	  }
	  	  
	  if (IsControlDisplay(ageRadioButton)) {
		  ageRadioButton.click();
	  }
	  
	  Thread.sleep(5000);
  }
  
  public boolean IsControlDisplay (WebElement element) {
	  return element.isDisplayed();
  }

@Test
  public void TC_02_IsEnable(){
	  WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='mail']"));
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	  WebElement jobSelect = driver.findElement(By.xpath("//select[@id='job1']"));
	  WebElement interestsCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
	  WebElement sliderRange = driver.findElement(By.xpath("//input[@id='slider-1']"));
	  WebElement buttonEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
	  
	  IsControlEnable(emailTextBox);
	  IsControlEnable(ageRadioButton);
	  IsControlEnable(educationTextArea);
	  IsControlEnable(jobSelect);
	  IsControlEnable(sliderRange);
	  IsControlEnable(interestsCheckbox);
	  IsControlEnable(buttonEnable);
	  
	  //
	  WebElement passwordlTextBox = driver.findElement(By.xpath("//input[@id='password']"));
	  WebElement ageRadioButtonDisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
	  WebElement biographyTextArea = driver.findElement(By.xpath("//textarea [@id='edu']"));
	  WebElement job2Select = driver.findElement(By.xpath("//select[@id='job2']"));
	  WebElement interestsCheckboxDisable = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
	  WebElement slider2Range = driver.findElement(By.xpath("//input[@id='slider-2']"));
	  WebElement buttonDisable = driver.findElement(By.xpath("//button[@id='button-disabled']"));
	  
	  IsControlEnable(passwordlTextBox);
	  IsControlEnable(ageRadioButtonDisable);
	  IsControlEnable(biographyTextArea);
	  IsControlEnable(job2Select);
	  IsControlEnable(interestsCheckboxDisable);
	  IsControlEnable(slider2Range);
	  IsControlEnable(buttonDisable);
	  
  }
  
  public void IsControlEnable (WebElement element) {
	   if (element.isEnabled()) {
		  System.out.println("Element is enabled");
	   }else {
		   System.out.println("Element is disable");
	   }
		   
  }
  
  @Test
  public void TC_03_IsSelected() throws Exception{
	  
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement interestsCheckbox = driver.findElement(By.xpath("//input[@id='development']"));
	  
	  if (IsControlDisplay(ageRadioButton) && IsControlDisplay(interestsCheckbox)) {
		  ageRadioButton.click();
		  interestsCheckbox.click();
	  }
	 
	  IsControlSelected (ageRadioButton);
	  IsControlSelected (interestsCheckbox);
	  
	  Thread.sleep(5000);
  }
  
  public void IsControlSelected (WebElement element) {
	   if (!element.isSelected()) {
		  element.click();
	   }
	   
		   
 }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
