package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class basesingle {
   static WebDriver driver;
        
    //   @BeforeTest
    //   @Parameters({"browser"})
      public static WebDriver creatDriver(String browser){
       
      try {
               switch (browser.toLowerCase()) {
                   case "chrome":
                     WebDriverManager.chromedriver().setup();
                     driver = new ChromeDriver();
                       break;
                   case "edge":
                    //    EdgeOptions edgeOptions = new EdgeOptions();
                    WebDriverManager.edgedriver().setup();
                       driver = new EdgeDriver();
                       break;
                   case "firefox":
                      WebDriverManager.firefoxdriver().setup();
                    //    FirefoxOptions firefoxOptions = new FirefoxOptions();
                       driver = new FirefoxDriver();
                       break;
                   default:
                       System.out.println("Invalid browser specified");
                       throw new IllegalArgumentException("Invalid browser: " + browser);
        
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error during browser setup: " + e.getMessage());
            throw e;  // Rethrow the exception to stop the test
        }
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        System.out.println("WebDriver initialized: " + driver.getClass().getSimpleName());
        return driver; 
    
}
// @AfterTest
// public void tearDown(){
//     if (driver != null) {
//         driver.quit();  // Close the browser after each test
//     }
// }
public WebDriver getDriver() {
    return driver;
}
public static void launchurl(String AppUrl){
    if(driver!=null){
        driver.get(AppUrl);
    }
}

}
