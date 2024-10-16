package baselibrary;

import java.awt.Desktop.Action;
import java.awt.Event;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.runners.model.FrameworkField;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ApplicationUtility.Applicationutility;
import excelutility.ExcelUtility;
import propertyutility.Propertyutility;
import screenshotutility.Screenshotutility;

public class BaseLibrary implements ExcelUtility , Propertyutility , Applicationutility , Screenshotutility{
	
  public static WebDriver driver = null;
	public void getlaunch(String browser) {
		
		if (browser.equals("chrome")) {
			String path = "C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\webdriver1\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
			 driver = new ChromeDriver();
		}
		
		else if (browser.equals("firefox")) {
			String path = "C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\webdriver1\\chromedriver.exe";
			System.setProperty("webdriver.firefox.driver", path);
			 driver = new FirefoxDriver();
		}
		

		else if (browser.equals("edge")) {
			String path = "C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\webdriver1\\chromedriver.exe";
			System.setProperty("webdriver.edge.driver", path);
			 driver = new EdgeDriver();
		}
		String path = "C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\webdriver1\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		 driver = new ChromeDriver();
		driver.get("http://www.testingbaba.com/old/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	WebElement close=driver.findElement(By.xpath("//button[text()='×']"));
	waitforclick(close);
	WebElement practice	=driver.findElement(By.xpath("//a[text()='Practice']"));
		waitforclick(practice);
	}
	public String getReadData(int sheet, int row, int col) {
		String path = "C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\testData\\New Microsoft Excel Worksheet.xlsx";
		String val = "";
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sht = wb.getSheetAt(sheet);
			 val = sht.getRow(row).getCell(col).getStringCellValue();
		} catch (Exception e) {
			System.err.println("issue in getreadData : " + e);
		}
		return val;
		
	}
	@Override
	public String getReadDatat(String key) {
		
		String path="C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\testData\\pooja.properties";
		String value = "";
		try {
		
			FileInputStream fis = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(fis);
			 value =prop.getProperty(key);
		} catch (Exception e) {
			
			System.err.println("issue int get read data " +e);
		}
		return value;
	}
	@Override
	public void doubleclick(WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele).perform();
	}
	
	@Override
	public void rightclick(WebElement ele) {
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
		
	}
	@Override
	public void click(WebElement ele) {
		Actions act = new Actions(driver);
		act.click(ele).perform();
		
	}
	@Override
	public void switchonwindow(int index) {
	  Set<String> handle =  driver.getWindowHandles();
	  ArrayList<String> handles = new ArrayList<String>(handle);
	  driver.switchTo().window(handles.get(index));
		
	}
	@Override
	public void waitforclick(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();//it work in both ways there is no need to write close. click where waitforclick it click automatically
	}
	@Override
	public void waitforvisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	@Override
	public void waitforAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		
	}
	@Override
	public void handlepromptalert(String val) {
		driver.switchTo().alert().sendKeys(val);
		
	}
	@Override
	public void confirmboxalert() {
		driver.switchTo().alert().dismiss();
		
	}
	@Override
	public void alertwait() {
		driver.switchTo().alert().accept();
		
	}
	@Override
	public void alertsee() {
		driver.switchTo().alert().accept();
		
	}
	@Override
	public void selectbyvisibletext(WebElement ele, String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
		
	}
	@Override
	public void selectbyindex(WebElement ele, int index) {
		Select sel = new Select(ele);
		sel.selectByIndex(index);
		
	}
	@Override
	public void selectbyvalue(WebElement ele, String val) {
		Select sel = new Select(ele);
		sel.selectByValue(val);
		
	}
	@Override
	public void selectalloption(WebElement ele) {
		Select sel = new Select(ele);
		List<WebElement> list =sel.getOptions();
		for (int i = 0; i <list.size(); i++) {
			 String st =list.get(i).getText();
			System.err.println(st);
		}
		
	}
	@Override
	public void selectmultiple(WebElement ele) {
		Select sel = new Select(ele);
		List<WebElement> list =sel.getOptions();
		for (int i = 0; i <list.size(); i++) {
			 String st =list.get(i).getText();
			System.err.println(st);
		}
		
	}
	@Override
	public String getreaddata(String key) {
		String path="C:\\Users\\Naveen\\eclipse-workspace\\Testing_babaProjects1stApril\\testData\\propertiestooltips";
		String value = "";
		try {
		
			FileInputStream fis = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(fis);
			 value =prop.getProperty(key);
		} catch (Exception e) {
			
			System.err.println("issue int get read data " +e);
		}
		
		return value;
	}
	@Override
	public void uploadfile(String path) {
		try {
			StringSelection sel = new StringSelection(path);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(sel, null);
			
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			rob.delay(250);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_V);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.delay(250);
			rob.keyRelease(KeyEvent.VK_ENTER);
				
		} catch (Exception e) {
			System.err.println("issue in upload file : " + e);
		}
		
	}
	@Override
	public void getscreenshot(String foldername, String filename) {
		String loc = System.getProperty("user.dir");
		String path = loc+"//screenshot// " + foldername +"//"+ filename + ".png";
		try {
			EventFiringWebDriver efw = new EventFiringWebDriver(driver);
			  File src=efw.getScreenshotAs(OutputType.FILE);
			  File des = new File(path);
			  FileUtils.copyFile(src, des);
			
		} catch (Exception e) {
			System.err.println("issue in get screenshot:" + e);
		}
	}
	@AfterMethod
	public void getResultAnalysis(ITestResult result) {
		String filename = result.getMethod().getMethodName();
	if (result.getStatus()==ITestResult.SUCCESS) {
			getscreenshot("Pass",filename);
		}
	else if (result.getStatus()==ITestResult.FAILURE) {
		
		getscreenshot("Fail", filename);
	}
		
	}
	@ Test
	public void flush () {
		
		driver.close();
	}
	}


