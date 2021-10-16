package com.example.scraping;

import com.example.entity.Product;
import com.example.WebDriverCustom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Scraping {

    private static final String url = "https://www.tokopedia.com/p/handphone-tablet";

    private static final String productList = "//div[@data-testid='master-product-card']/div";
    private static final String productName = "//h1[@data-testid='lblPDPDetailProductName']";
    private static final String productDescription = "//div[@data-testid='lblPDPDescriptionProduk']";
    private static final String productImageLink  = "//div[@data-testid='PDPImageMain']//img";
    private static final String productPrice = "//*[@data-testid='lblPDPDetailProductPrice']";
    private static final String productRating = "//span[@data-testid='lblPDPDetailProductRatingNumber']";
    private static final String merchantName = "//*[@data-testid='llbPDPFooterShopName']//h2";

    private List<String> linkProductList;
    private List<Product> products;
    private int count = 100;

    private WebDriverCustom webDriver;

    public void finish() {
        webDriver.quit();
    }
    public void getLinkProductList() {
        webDriver = new WebDriverCustom();
        List<String> tabs = webDriver.prepareTwoTabs();
        webDriver.setUrl(url, tabs.get(0));
        linkProductList = new ArrayList<>();
        Product product;
        products = new ArrayList<>();
        try{
            while(products.size()!=count) {
                List<WebElement> productLists = webDriver.getElementListByXpath(productList);

                for (WebElement webElement : productLists) {
                    try {
                        String path = webElement.findElement(By.tagName("a")).getAttribute("href");
                        if(!linkProductList.contains(path))
                            webDriver.setUrl(path,tabs.get(1));
                            System.out.println(path);
                            webDriver.scroll();
                            webDriver.waitUntilElementVisible(productName);
                            String name = webDriver.getTextByElement(productName);
                            String description = webDriver.getTextByElement(productDescription);
                            String imageLink = webDriver.getTextByElementAttribute(productImageLink,"src");
                            String price = webDriver.getTextByElement(productPrice).replace("Rp","").replace(".","");
                            webDriver.waitUntilElementVisible(productRating);
                            String rating = webDriver.getTextByElement(productRating);
                            String merchant = webDriver.getTextByElement(merchantName);

                            product = new Product(name, description, imageLink, price, rating, merchant);

                            products.add(product);
                            linkProductList.add(path); // for avoid duplication
                    }catch (Exception e){
                        webDriver.switchTab(tabs.get(0));
                        continue;
                    }
                    if(products.size()%10==0){
                        System.out.println(products.size());
                    }


                    if(products.size()==count){
                        break;
                    }
                    webDriver.switchTab(tabs.get(0));
                }
            }
            System.out.println(products.size());
        }catch (Exception e){
            e.printStackTrace();
            webDriver.switchTab(tabs.get(0));
        }finally {
            webDriver.close();
        }
    }


    public List<Product> getProductList(){
        return products;
    }
}
