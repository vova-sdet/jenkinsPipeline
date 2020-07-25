package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage {

    public RegisterPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//ul[@id='formBreadcrumb']//span")
    public List<WebElement> headers;

    @FindBy(name = "givenName")
    public WebElement givenName;

    @FindBy(name = "familyName")
    public WebElement familyName;

    @FindBy(id = "gender-field")
    public WebElement gender;

    @FindBy(id = "birthdateYears-field")
    public WebElement estimatedYears;

    @FindBy(id = "birthdateMonths-field")
    public WebElement estimatedMonths;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id = "cityVillage")
    public WebElement city;

    @FindBy(id = "stateProvince")
    public WebElement state;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id="postalCode")
    public WebElement zipCode;

    @FindBy(name="phoneNumber")
    public WebElement phoneNumber;

    @FindBy(id = "relationship_type")
    public WebElement relationShipType;

    @FindBy(className = "person-typeahead")
    public WebElement personName;

    @FindBy(id="submit")
    public WebElement confirmButton;

    @FindBy(xpath = "//i[contains(@class,'icon-minus-sign')]")
    public List<WebElement> minusButtons;

    @FindBy(xpath = "//div[@id='dataCanvas']//p")
    public List<WebElement> confirmationInfo;

    @FindBy(className = "icon-home")
    public WebElement homeButton;

    public void deleteRelation(){

        for(WebElement minusButton :minusButtons){
            minusButton.click();
        }

    }

    // I need to store my business requirement for headers.
    public List<String> getHeaders(){
        List<String> headers=new ArrayList<>();
        headers.add("Demographics");
        headers.add("Name");
        headers.add("Gender");
        headers.add("Birthdate");
        headers.add("Contact Info");
        headers.add("Address");
        headers.add("Phone Number");
        headers.add("Relationships");
        headers.add("Relatives");
        headers.add("Confirm");
    return headers;
    }

    public void clickHeaders(String headerName){
        for(WebElement header: headers){
            if(header.getText().trim().equals(headerName)){
                header.click();
                break;
            }
        }
    }


}
