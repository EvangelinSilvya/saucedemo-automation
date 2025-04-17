package Utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        test = getInstance().createTest(name);
        return test;
    }

    public static void flush() {
        getInstance().flush();
    }
}
