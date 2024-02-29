package core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page 
{
	public static WebDriver driver = null;
	public static ExtentReports report = null;
	public static ExtentTest test = null;
	
  @Parameters({"browser","url","reportname","key"})	
  @BeforeTest
  public void openBrowser(String browser,String url,String reportname,String key) throws Exception
  {
	  if(!Boolean.parseBoolean(key))
	  {
		  throw new SkipException("Skip test..");
	  }
	  else
	  {
		  // Report
		  String reportpath = System.getProperty("user.dir")+"\\src\\test\\java\\reports\\"+reportname+".html";
	      report = new ExtentReports(reportpath);
	      test = report.startTest(reportname);
	      
	      // Browser launch..
	      if(browser.equalsIgnoreCase("chrome"))
	    	{
	    		driver = new ChromeDriver();
	    	}
	    	else if(browser.equalsIgnoreCase("edge"))
	    	{
	    		driver = new EdgeDriver();
	    	}
	    	else if(browser.equalsIgnoreCase("firefox"))
	    	{
	    		driver = new FirefoxDriver();
	    	}
	    	
	    	
	    	driver.navigate().to(url); // prefered - can go back / forward
	    	
	    	  test.log(LogStatus.PASS, "browser "+browser+" url "+url+" opens..");
	    	
	    	PageFactory.initElements(driver, this); // compulsory to read external xpath
	    	
	    	 // implicit wait..
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20L));
	    	
	    	driver.manage().window().maximize();

	      
	  }
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.quit();
	  report.endTest(test);
  	  report.flush();
  }

}
