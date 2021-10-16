package com.example;

import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WebDriverCustom {
    private static final String tokpedUrl = "https://tokopedia.com";
    private static final String userAgent =
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;


    public WebDriverCustom() {
        System.setProperty("webdriver.chrome.driver","chromedriver94.0.4606.61.exe");
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.setHeadless(true);
        optionsChrome.addArguments(userAgent);

        driver =  new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        jsExecutor = (JavascriptExecutor) driver;
    }
    
    public void quit(){
        driver.quit();
    }

    public void switchTab(String tab) {
        driver.switchTo().window(tab);
    }

    public List<String> prepareTwoTabs() {
        driver.get(tokpedUrl);
        jsExecutor.executeScript("window.open()");
        return new ArrayList<>(driver.getWindowHandles());
    }

    public void setUrl(String Url, String tab){
        switchTab(tab);
        driver.get(Url);
    }

    public List<WebElement> getElementListByXpath(String findElement){
        jsExecutor.executeScript("window.scrollBy(0,600)");
        return driver.findElements(By.xpath(findElement));
    }

    public void waitUntilElementVisible(String element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(element)));
    }

    public void scroll(){
        jsExecutor.executeScript("window.scrollBy(0,300)");
    }

    public String getTextByElement(String element){
        return driver.findElement(By.xpath(element)).getText();
    }

    public String getTextByElementAttribute(String element, String attribute){
        return driver.findElement(By.xpath(element)).getAttribute(attribute);
    }

    public void close(){
        driver.close();
    }
}
