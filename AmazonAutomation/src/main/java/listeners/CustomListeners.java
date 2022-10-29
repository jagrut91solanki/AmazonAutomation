package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.amazon.base.TestBase;
import com.amazon.util.CommonUtils;
import com.amazon.util.Variables;
import com.amazon.util.monitoringMail;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {
	public String messageBody;
	public boolean flag;

	public void onFinish(ITestContext arg0) {
		//System.out.println(arg0 + " =================Test Case Execution Finished ===========================");
	}

	public void onStart(ITestContext arg0) {
		//System.out.println(arg0 + " =================Test Case Execution Started===========================");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		//System.out.println(arg0 + " =================Test Case Failed===========================");
		System.out.println("===========================================================");
		System.out.println("Test Case Execution Got Failed,Test Name is:\t"+arg0.getName());
		System.out.println("===========================================================");
		flag = true;
		System.out.println("Flag is inside onTestFailure " + flag);
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			commonUtil.captureScreenshot();
			test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " Failed with exception : " + arg0.getThrowable());
			test.log(LogStatus.FAIL, test.addScreenCapture(commonUtil.screenshotName));
			Reporter.log("Click to see Screenshot");
			Reporter.log("<a target=\"_blank\" href=" + commonUtil.screenshotName + ">Screenshot</a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + commonUtil.screenshotName + "><img src="
					+ commonUtil.screenshotName + " height=200 width=200></img></a>");
			commonUtil.switchTab("0");
			rep.endTest(test);
			rep.flush();
			CommonUtils.zip(System.getProperty("user.dir") + "\\target\\surefire-reports\\AmazonTest");
		} catch (IOException e) {
			System.err.println("IOException occurs " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult arg0) {
		
		System.out.println("===========================================================");
		System.out.println("Test Case is Skipped,Test Name is:\t"+arg0.getName());
		System.out.println("===========================================================");
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " Skipped the test as the Run mode is NO");
		rep.endTest(test);
		rep.flush();

	}

	public void onTestStart(ITestResult arg0) {

		//System.out.println(arg0 + " =================Test Started===========================");
		test = rep.startTest(arg0.getName().toUpperCase());

	}

	public void onTestSuccess(ITestResult arg0) {

		test.log(LogStatus.PASS, arg0.getName().toUpperCase() + " PASS");
		rep.endTest(test);
		rep.flush();

	}

	public void onFinish(ISuite arg0) {
		//System.out.println(arg0 + " =================Test Case Execution Finished ===========================");
		System.out.println("===========================================================");
		System.out.println("Test Case Executed Sucesfully,Test Name is:\t"+arg0.getName());
		System.out.println("===========================================================");
		System.out.println("Flag is inside onFinish " + flag);
		if (flag == true) {
			try {
				monitoringMail mail = new monitoringMail();
				mail.sendMail(Variables.server, Variables.from, Variables.to, Variables.subject, Variables.messageBody,
						Variables.attachmentPath, Variables.attachmentName);
			} catch (AddressException e) {
				System.out.println("Address Exception occur" + e.getMessage());
				e.printStackTrace();
			} catch (MessagingException e) {
				System.out.println("MessagingException occur" + e.getMessage());
				e.printStackTrace();
			}
		} else
			flag = false;
		System.out.println("Flag is converted inside onFinish " + flag);

	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub

		//System.out.println(arg0 + " =================Test Case Execution Started ===========================");
		System.out.println("===========================================================");
		System.out.println("Test Case Execution Started,Test Name is:\t"+arg0.getName());
		System.out.println("===========================================================");
		flag = false;
		System.out.println("Flag is inside onStart and it is  " + flag);
		File index = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\AmazonTest");
		try {
			CommonUtils.delete(index);
		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
			e.printStackTrace();
		}
		CommonUtils.makeDirectory();
	}

}
