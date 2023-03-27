package com.bridgelabz.base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;

    public Properties prop;

    public WebDriver init_driver(String browserName) {
        ChromeOptions option = new ChromeOptions();
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            if (prop.getProperty("headless").equals("yes")) {
                option.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(option);
            } else {
                option.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(option);
            }
        }
        return driver;
    }

    public Properties init_Properties() {
        prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\atulk\\IdeaProjects\\Facebook_keyworddriven\\config.properties");
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}