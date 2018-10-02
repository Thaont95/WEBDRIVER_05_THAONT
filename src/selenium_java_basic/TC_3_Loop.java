package selenium_java_basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TC_3_Loop {
	public static void main(String[] args) {
		WebDriver driver;
		List<WebElement> elements;
		int i= 0;
		int y = 0;
		
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		elements = driver.findElements(By.xpath("//a"));
		
		int number = elements.size();
		
		//for-each
		for (WebElement element :elements ) {
			System.out.println("link in for-each"+ element.getAttribute("href"));
		}
		
		//while
		while(i< number) {
			System.out.println("link in while"+i+elements.get(i).getAttribute("href"));
			i++;
		}
		
	}

}

