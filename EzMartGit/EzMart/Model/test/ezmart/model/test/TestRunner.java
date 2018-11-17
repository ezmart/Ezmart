package ezmart.model.test;

import org.junit.runner.JUnitCore;

public class TestRunner {

    public static void main(String[] args) {
        
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Marcos Paulo\\Desktop\\DriverTest\\geckodriver.exe");

        JUnitCore.runClasses(TestSuiteEzMart.class);
    }

}
