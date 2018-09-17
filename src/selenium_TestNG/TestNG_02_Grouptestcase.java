package selenium_TestNG;

import org.testng.annotations.Test;

public class TestNG_02_Grouptestcase {
  @Test(groups ="customer", priority = 1)
  public void TC_01() {
	  System.out.println("TC 01");
  }
  
  @Test(groups ="Payment")
  public void TC_02() {
	  System.out.println("TC 02");
  }
  
  @Test(groups ="Manage")
  public void TC_03() {
	  System.out.println("TC 03");
  }
  
  @Test(groups ="Payment", enabled = false)
  public void TC_04() {
	  System.out.println("TC 04");
  }
  
  @Test(groups ="customer")
  public void TC_05() {
	  System.out.println("TC 05");
  }
}
