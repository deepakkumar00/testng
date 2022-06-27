package Automation.TestNG_Assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_2006 {
	WebDriver driver;
	String username = "randomuser122";
	String password = "user@123";
	
	@BeforeMethod
	public void init(){
		System.setProperty("webdriver.gecko.driver", ".//lib//geckodriver.exe");
		driver = new FirefoxDriver();
		String baseURL = "http://elearningm1.upskills.in/";
		try{
			driver.get(baseURL);
		}
		catch(Exception e){
			System.out.println("Could Not Launch Website!");
		}
	}
	
	@Test(priority = 0)
	public void signup(){
		driver.findElement(By.linkText("Sign up!")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("John");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Doe");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("doe.john@demo.com");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='pass1']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='pass2']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@name='submit']")).click();
		WebElement ele = null; 
		try
		{
			ele = driver.findElement(By.xpath("//p[contains( text(), 'Dear')]"));
		}
		catch(Exception e)
		{
			System.out.println("Element Not found... Failed to signup!");
		}
		finally
		{
			try
			{
				Assert.assertNotNull(ele);
			}
			catch(Exception e)
			{
				System.err.println("Assertion Failed");
			}
		}
		
	}
	
	@Test(priority = 1, enabled = false)
	public void login()
	{
		driver.findElement(By.xpath("//input[@name='login']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@name='submitAuth']")).click();
	}
	
	@Test(priority = 2, enabled=false)
	public void email() throws InterruptedException
	{
		login();
		driver.findElement(By.partialLinkText("Compose")).click();
		
		driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("dee");
		Thread.sleep(2000);
		List<WebElement>
		lst = driver.findElements(By.xpath("//ul[@class='select2-results__options']/li"));
		int i = (int)(Math.random()*(lst.size()-0+1)+0);  
		lst.get(i).click();
		
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Subject");
		
		driver.findElement(By.xpath("//button[@name='compose']")).click();
		
		try
		{
			WebElement ele = driver.findElement(By.xpath("//*[contains(text(), 'sent')]"));
			Assert.assertNotNull(ele);
		}
		catch(Exception e)
		{
			System.out.println("Could Not send message!");
		}
	}
	
	@AfterMethod
	public void end(){
		driver.close();
	}

}
