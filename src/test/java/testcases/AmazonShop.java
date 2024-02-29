package testcases;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import core.Hook;

public class AmazonShop 
{
  @Test
  public void shop() throws Exception 
  {
	  if(AmazonLogin.login == true)
		{
			Hook.searchbox.sendKeys("Sun Glasses");
			  
			   Hook.test.log(LogStatus.INFO, "searching Sun glasses..  ");
			   
			Actions a = new Actions(Hook.driver);
			a.sendKeys(Keys.ENTER).perform();
			
			utility.ScreenShot.takeScreenShot("searchresult");
			
	Iterator <WebElement> it =	Hook.searchresult.iterator();
	
	while(it.hasNext())
	{
		WebElement p = it.next();
		System.out.println(p.getText());
		
		   if(p.getText().contains("Retro"))
		   {
			   Hook.driver.findElement(By.xpath("//*[text() ='"+p.getText()+"']")).click();
		      Thread.sleep(4000);
			Set <String> tabs =   Hook.driver.getWindowHandles();
			Iterator <String> t=  tabs.iterator();
			String currtab = t.next();
			String prodtab = t.next();
		   Hook.driver.switchTo().window(prodtab);
		   
		   Hook.kartbtn.click();
		   
		   break;
		   }
	}
		}
		
		
	
  }
}
