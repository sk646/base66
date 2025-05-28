package basetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testcases{
  WebDriver driver;
   @BeforeTest
   public void starup(){
    driver= basesingle.creatDriver("chrome");
    basesingle.launchurl("https://www.google.co.in/");
    
   }
  @Test(enabled=false)
  public void launch() throws InterruptedException{
    if (driver == null) {
      System.out.println("Driver is not initialized!");
      return;
  } 
  //  driver.get("https://www.amazon.in/");
   System.out.println("Testcase started");
   driver.manage().window().maximize();
   System.out.println(driver.getTitle());
   Assert.assertEquals(driver.getTitle(), "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
   WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
   WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
   Actions ac = new Actions (driver);
   searchbox.clear();
   ac.moveToElement(searchbox).click();
   ac.sendKeys(searchbox,"music instrument").perform();
   Thread.sleep(3000);
   searchbox.sendKeys(Keys.ENTER);
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'s-main-slot')]/descendant::div[contains(@class,'puis-card-container')]")));
   List<WebElement>lists=driver.findElements(By.xpath("//div[contains(@class,'puis-padding-right-small')]"));
  
    if(!lists.isEmpty()){
    WebElement lastElement = lists.get(lists.size()-1);
    String Title = lastElement.findElement(By.xpath(".//a[contains(@class,'s-line-clamp')]")).getText();
    String priceTag = lastElement.findElement(By.xpath(".//span[contains(@class,'a-price-whole')]")).getText();
    System.out.println("product tile is: "+Title);
    System.out.println("priceis: "+priceTag);
    }
    Thread.sleep(3000);
    for(WebElement list:lists){
      HashMap<String,Integer> prodlist = new HashMap<>();
      String productPriceTag = list.findElement(By.xpath(".//span[contains(@class,'a-price-whole')]")).getText();
      int productPrice = Integer.parseInt(productPriceTag.replaceAll("[^0-9]",""));
      if(productPrice>1000){
       String ProductTitle = list.findElement(By.xpath(".//a[contains(@class,'s-line-clamp')]")).getText();
       prodlist.put(ProductTitle,productPrice);
      }
      for(Map.Entry<String,Integer> m:prodlist.entrySet()){
        System.out.println("Music instrumentPrice: "+m.getKey()+" "+"Music instrumentTitle: "+m.getValue());
    }
   
  }
  System.out.println("Testcase ended");
}
@Test(enabled=true)
public void testcase02() throws InterruptedException{
//  driver.get("https://www.google.co.in/");
Actions ad = new Actions (driver);
WebElement serchbox = driver.findElement(By.xpath("//textarea[@name='q']"));
ad.moveToElement(serchbox).click();
// serchbox.sendKeys("Capgemini");
ad.sendKeys(serchbox,"Capgemini").build().perform();
WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(6));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Alh6id")));
serchbox.sendKeys(Keys.ENTER);
driver.switchTo().newWindow(WindowType.TAB);
driver.navigate().to("https://www.apsrtconline.in/oprs-web/guest/home.do?h=1");
ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
driver.switchTo().window(tabs.get(1));
Thread.sleep(3000);
driver.switchTo().window(tabs.get(0));
System.out.println("Testcase ended");
}

@AfterTest
public void tearDown(){
    if (driver != null) {
        driver.quit();  // Close the browser after each test
    }
}

}
