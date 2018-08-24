package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_Uploadfile {
	WebDriver driver;
    //duong dan tuyet doi
	//D:\Automation testing\Online_Class_05\WEBDRIVER_05_THAONT\images\img1.png
	//Lay ra duong dan thu muc minh dang dung
	String ProjectDirectory = System.getProperty("user.dir");
	// lay duong dan tuong doi
	String filename= "img1.png";
	String uploadFilePath = ProjectDirectory + "\\images\\" +filename;
	
	String ChromeUpload = ProjectDirectory +"\\upload\\chrome.exe";
	String FirefoxUpload = ProjectDirectory +"\\upload\\firefox.exe";
	String IeUpload = ProjectDirectory +"\\upload\\ie.exe";
	
	String foldername = "Online05" + randomnumber();
	String email = "Automation" + randomnumber() + "@gmail.com";
	String fname = "Online05";
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
//	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//	  driver = new ChromeDriver();
	  
//	  System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//	  driver = new InternetExplorerDriver();
	  
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

//@Test
  public void TC_01() {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	WebElement uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
	uploadfile.sendKeys(uploadFilePath);
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='"+filename+"']")).isDisplayed());
	
	driver.findElement(By.xpath("//td//button[@class='btn btn-primary start']")).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+filename+"']")).isDisplayed());

  }
  
 // @Test
  public void TC_02_AutoIT() throws Exception {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
	uploadfile.click();
	
	Runtime.getRuntime().exec(new String[] { FirefoxUpload, uploadFilePath });
	
	//verify upload
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='"+filename+"']")).isDisplayed());
	
	driver.findElement(By.xpath("//td//button[@class='btn btn-primary start']")).click();
	Thread.sleep(3000);
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+filename+"']")).isDisplayed());
  }
  
 // @Test
  public void TC_03_Robot() throws Exception {
	driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	
	WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
	uploadfile.click();
	
	//define location cua filename
	StringSelection select = new StringSelection(uploadFilePath);
	
	//Copy to clipboard
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

	//Click
	driver.findElement(By.className("fileinput-button")).click();

	Robot robot = new Robot();
	Thread.sleep(1000);

	//focus vao textbox
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	//gia lap nhan phim ctrl+V
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);

	//gia lap nha phim ctrl+V
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyRelease(KeyEvent.VK_V);
	Thread.sleep(1000);

	//enter
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	//verify upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() ='"+filename+"']")).isDisplayed());
		
		driver.findElement(By.xpath("//td//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='"+filename+"']")).isDisplayed());
  }
  
  @Test
  public void TC_04() throws Exception {
	driver.get("https://encodable.com/uploaddemo/");
	
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath);
	
	WebElement Uploaddropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
	Select select = new Select(Uploaddropdown);
	select.selectByVisibleText("/uploaddemo/files/");
	
	driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(foldername);
	driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(email);
	driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(fname);
	
	driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='Email Address: "+email+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dd[text()='First Name: "+fname+"']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dt//a[text()='"+filename+"']")).isDisplayed());
	
	driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
	
	driver.findElement(By.xpath("//a[text()='"+foldername+"']")).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+filename+"']")).isDisplayed());
	
	
	
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int randomnumber() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
  }

}
