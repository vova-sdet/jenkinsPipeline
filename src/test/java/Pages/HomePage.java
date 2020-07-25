package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h4")
    public WebElement loginText;

    @FindBy(xpath = "//li[contains(@class,'logout')]")
    public WebElement logOutButton;

    @FindBy(xpath = "//div[@id='apps']//a")
    public List<WebElement> applications;

    // The method will get name of the application and it will click if the name is matching

    public void clickApplication(String nameOfApplication){
        for(WebElement app: applications){
            if(app.getText().trim().equals(nameOfApplication)){
                app.click();
                break;
            }
        }
    }




}
