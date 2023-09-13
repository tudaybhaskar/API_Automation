package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import helper.BaseTestHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extentReport = null ;
    private ExtentTest extentTest;
    public static ExtentReports initializeExtentReport()
    {
        if( extentReport == null )
        {
            String folderPath = System.getProperty("user.dir") + "/resources/reports/" + BaseTestHelper.TimeStamp();
            BaseTestHelper.createFolder(folderPath);
            String reportFileName = "extentReport.html";
            String filePath = folderPath+"/"+reportFileName;

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
            extentReport = new ExtentReports();
            extentReport.setSystemInfo("Host Name",System.getProperty("user.name"));
            extentReport.setSystemInfo("Environment", "QA");
            extentReport.setSystemInfo("OS","Mac OS X");
            extentReport.config();
            extentReport.attachReporter(htmlReporter);

        }
        return extentReport;
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        String methodName = result.getName();
        System.out.println( "Test Method name : " + methodName );

        if(extentTest != null){
            extentTest.log(Status.PASS," Test method successful : " + methodName) ;
        }
    }
}
