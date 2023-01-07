package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TaskExecution {

	@Test(dataProvider = "testData")
	public void assignment_Script(String username,String password) throws Exception 
	{
		Reporter.log("TC to test Script to validate fuctional testing scenario started",true);
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sakshingp.github.io/assignment/login.html");
		Reporter.log("Chrome Browser Launched and navigated to URL : ",true);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement userName=driver.findElement(By.id("username"));
		WebElement pwd=driver.findElement(By.id("password"));
		WebElement loginBtn=driver.findElement(By.id("log-in"));
		String titleBeforeLogin=driver.getTitle();
		Reporter.log("Title Before Login : "+titleBeforeLogin,true);
			
		userName.sendKeys(username);
		pwd.sendKeys(password);
		loginBtn.click();
		Reporter.log(username,true);
		Reporter.log(password,true);

		String titleAfterLogin=driver.getTitle();
		Reporter.log("Title After Login : "+titleBeforeLogin,true);
		SoftAssert asserts=new SoftAssert();
		asserts.assertEquals(titleAfterLogin,titleBeforeLogin);
		Reporter.log("Login Successful",true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//th[contains(text(),'Amount')]")).click();
			 
		List<WebElement> options=driver.findElements(By.xpath("//td[@class='text-right bolder nowrap']"));
		ArrayList originalList=new ArrayList();
		ArrayList tempList=new ArrayList();
			 
		for(WebElement option:options)
		{
			originalList.add(option.getText());
			tempList.add(option.getText());
		 }
		Collections.sort(tempList);
		Reporter.log("original List : "+originalList,true);
		Reporter.log("Temp List : "+tempList,true);
			 
			 
		 if(originalList.equals(tempList))
		 {
			Reporter.log("Amounts are in Sorted order...",true);
		  }
		 else
		 {
			Reporter.log("Amounts are Not in Sorted order...",true);
		 }
		 
		driver.quit();
		
		 }
		@DataProvider
		public Object[][] testData()
		{
			Object obj[][]=new Object[9][2];
			obj[0][0]="surajamburu";
			obj[0][1]="Relevel";
			obj[1][0]="SURAJ";
			obj[1][1]="RELEVEL";
			obj[2][0]="Gmail";
			obj[2][1]="abcd";
			obj[3][0]="12113";
			obj[3][1]="121412";
			obj[4][0]="111111111111111";
			obj[4][1]="000000000000000";
			obj[5][0]="aaaaaaaaaaa";
			obj[5][1]="bbbbbbbbbbb";
			obj[6][0]="@#&%*";
			obj[6][1]="*^*@%";
			obj[7][0]="";
			obj[7][1]="";
			obj[8][0]="abc@abc";
			obj[8][1]="Abc@224";
			return obj;
		}

}
