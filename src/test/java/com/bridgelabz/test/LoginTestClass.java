package com.bridgelabz.test;
import com.bridgelabz.keywordDriven.KeywordDrivenFrameworkEngine;
import org.testng.annotations.Test;

public class LoginTestClass {

    public KeywordDrivenFrameworkEngine keywordDrivenFrameworkEngine;

    @Test
    public void login_Using_KeywordDriven_Test() throws InterruptedException {
        keywordDrivenFrameworkEngine = new KeywordDrivenFrameworkEngine();
        keywordDrivenFrameworkEngine.start_Execution("Sheet1");
        Thread.sleep(5000);
    }
}
