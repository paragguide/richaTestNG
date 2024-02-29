package testcases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import core.Hook;

import org.testng.annotations.DataProvider;

public class AmazonLogin extends Hook
{
	public static boolean login = false;
	
  @Test(dataProvider = "logindata")
  public void login(String username, String password) throws Exception 
  {
	  signin.click();
	  test.log(LogStatus.INFO, "testing uid "+username+" pwd "+password);
	  
	     uid.clear();	
		uid.sendKeys(username);
		ctnbtn.click();
		   try {
	   
	   test.log(LogStatus.INFO, err1.getText() );
	driver.navigate().to("https://www.amazon.in/");
	
	utility.ScreenShot.takeScreenShot("invaliduid");
		   }
		   catch(Exception e)
		   {
			   // uid is valid..
			   pwd.clear();
			   pwd.sendKeys(password);
			   submit.click();
			      try {
		   
		    test.log(LogStatus.INFO, err2.getText() );
		    utility.ScreenShot.takeScreenShot("invalidpwd");
		    driver.navigate().to("https://www.amazon.in");
		
			      }
			      catch(Exception ee)
			      {
			    	  // pwd is correct
			    	  test.log(LogStatus.PASS, "loged in sucsess");
			    	  login = true;
			    	  utility.ScreenShot.takeScreenShot("logedinsucsess");
			      }
		   }
	
		 
  }

  @DataProvider
  public Object[][] logindata() throws Exception 
  {
    return utility.WBConnection.makeWBConnection("AmazonLogin", "Sheet1");
  }
}
