package testcases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import core.Hook;

public class AmazonForgetPwd extends Hook
{
  @Test
  public void forget() throws Exception
  {
	  signin.click();
	  help.click();
	  forget.click();
	  uid.sendKeys("paragguide@yahoo.co.in");
	  ctnbtn.click();
	  utility.ScreenShot.takeScreenShot("AmazonForget");
	  test.log(LogStatus.PASS, "forget ..");
  }
}
