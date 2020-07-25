package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id="password")
    public WebElement password;

    //List<WebDriver> locations=driver.findElements(By.xpath("//ul[@id='sessionLocation']//li"));
    @FindBy(xpath = "//ul[@id='sessionLocation']//li")
    public List<WebElement> locations;

    @FindBy(id = "Pharmacy")
    public WebElement pharmacyButton;

    @FindBy(id = "loginButton")
    public WebElement loginButton;


    // get the data from excel file, csv, DB, Api Response, confluence page(Documentation page in the company)
    public List<String> getLocations(){
        List<String> locations=new ArrayList<>();
        locations.add("Inpatient Ward");
        locations.add("Isolation Ward");
        locations.add("Laboratory");
        locations.add("Outpatient Clinic");
        locations.add("Pharmacy");
        locations.add("Registration Desk");

        return locations;
    }

    // login()--> username, password, locationName
    public void login(String username, String password, String locationName){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        clickLocation(locationName);
        this.loginButton.click();

    }

    public void clickLocation(String locationName){
        for(WebElement location : locations){
            if(location.getText().trim().equals(locationName)){
                location.click();
            }
        }
    }


}
