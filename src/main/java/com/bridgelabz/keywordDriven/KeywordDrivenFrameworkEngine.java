package com.bridgelabz.keywordDriven;
import com.bridgelabz.base.BaseClass;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeywordDrivenFrameworkEngine extends BaseClass {
        public WebDriver driver;
        public Properties prop;

        public static Workbook book;
        public static Sheet sheet;

        public BaseClass base;
        public WebElement element;

        public final String SCENARIO_SHEET_PATH = "C:\\Users\\atulk\\IdeaProjects\\Facebook_keyworddriven\\src\\main\\resources\\Book1.xlsx";

        public void start_Execution(String sheetName) {
            FileInputStream file = null;
            try {
                file = new FileInputStream(SCENARIO_SHEET_PATH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                try {
                    book = WorkbookFactory.create(file);
                } catch (InvalidFormatException e) {
                    throw new RuntimeException(e);
                }
            } catch (EncryptedDocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheet = book.getSheet(sheetName);
            int k=0;
            for(int i=0; i<sheet.getLastRowNum(); i++) {
                try {
                    String locatorType = sheet.getRow(i+1).getCell(k+1).toString().trim();
                    String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();
                    String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
                    String value = sheet.getRow(i+1).getCell(k+4).toString().trim();

                    switch (action) {
                        case "open browser":
                            base = new BaseClass();
                            prop = base.init_Properties();
                            if(value.isEmpty() || value.equals("NA")) {
                                driver = base.init_driver(prop.getProperty("browser"));
                            }else{
                                driver = base.init_driver(value);
                            }
                            break;

                        case "enter url":
                            if(value.isEmpty() || value.equals("NA")) {
                                driver.get(prop.getProperty("url"));
                            }else {
                                driver.get(value);
                            }
                            break;
                        case "quit":
                            driver.quit();
                            break;
                        default:
                            break;
                    }

                    switch (locatorType) {
                        case "id":
                            element = driver.findElement(By.id(locatorValue));
                            if(action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if(action.equalsIgnoreCase("click")) {
                                element.click();
                            } else if(action.equalsIgnoreCase("isDisplayed")) {
                                element.isDisplayed();
                            }
                            locatorType=null;
                            break;

                        case "xpath":
                            element = driver.findElement(By.xpath(locatorValue));
                            if(action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if(action.equalsIgnoreCase("click")) {
                                element.click();
                            } else if(action.equalsIgnoreCase("isDisplayed")) {
                                element.isDisplayed();
                                System.out.println("Home Page is verified - User is able to sign in successfully");
                            } else {
                                System.out.println("User is not able to Sign In - Invalid Credential");
                            }
                            locatorType=null;
                            break;

                        default:
                            break;
                    }
                }
                catch(Exception e) {
                }
 }
}

        }
