package Tests.com.OpenMRS.LoginTest;


import DataSource.LoginData;
import Pages.HomePage;
import Pages.LoginPage;
import Tests.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class AdminLoginTest extends TestBase {

    // I need to create the object and I want to make this object is created
    // before everything is in class
    LoginPage page;
    HomePage homePage;

    @BeforeClass
    public void setupPage(){
        page=new LoginPage(driver);
        homePage=new HomePage(driver);
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://demo.openmrs.org/openmrs/login.htm");
    }


    @Test(priority = 1)
    public void validateLocations(){

        List<WebElement> allLocations=page.locations;
        for(WebElement location: allLocations){
            Assert.assertTrue(location.isDisplayed());
        }

    }

    @Test(priority = 2, dependsOnMethods = "validateLocations")
    public void validateLocationsText(){
        List<WebElement> actualText=page.locations;
        List<String> expectedText=page.getLocations();

        for(int i=0;i<actualText.size();i++){
            String actual=actualText.get(i).getText().trim();
            String expected=expectedText.get(i);

            Assert.assertEquals(actual,expected);
        }

    }


    @Parameters({"username","password","locationName"})
    @Test(priority = 3)
    public void validateLogin(String username, String password, String locationName){

        page.login(username,password,locationName);
        Assert.assertTrue(driver.getCurrentUrl().contains("home"));
        Assert.assertTrue(driver.getTitle().trim().equals("Home"));
        homePage.logOutButton.click();

    }

    //NOTE: dataProvider must have same value with your DataProvider name.
    // dataProviderClass must have same name with your class which has @DataProvider annotation.
    // if your data class is inside the another package, you should import it.
    @Test(priority = 4,dataProvider = "OpenMrsLocations",dataProviderClass = LoginData.class)
    public void validateLocationNames(String username, String password, String locationName){

        driver.manage().deleteAllCookies();
        page.login(username,password,locationName);
        String actualText=homePage.loginText.getText().trim();
        String expectedText="Logged in as Super User (admin) at "+locationName+".";
        Assert.assertEquals(actualText,expectedText);
        homePage.logOutButton.click();

    }



}
