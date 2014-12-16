package FunctionalTests.Pages;

import UsedByAll.Config;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 12/1/2014.
 */
class BasePage {  //abstract class
    protected String url = "http://" + Config.getConfig().getScheme();
    protected WebDriver driver;


    public BasePage(WebDriver driver){
        this.driver=driver;
    }
    public void open(){
        driver.get(url);
    }
    public boolean isOpened(){
        return driver.getCurrentUrl().equals(url);
    }
    public boolean isOpened(String url){
        return driver.getCurrentUrl().equals(url);
    }
}

