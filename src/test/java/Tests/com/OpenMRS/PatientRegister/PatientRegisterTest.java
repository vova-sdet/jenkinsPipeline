package Tests.com.OpenMRS.PatientRegister;

import DataSource.PatientRegisterData;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegisterPage;
import Tests.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class PatientRegisterTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;
    @BeforeClass
    public void setUpPage(){
        loginPage=new LoginPage(driver);
        homePage=new HomePage(driver);
        registerPage=new RegisterPage(driver);
    }

    @Parameters({"username","password","locationName","appName"})
    @Test(priority = 1)
    public void validateHeaders(String username, String password, String locationName,String appName){
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://demo.openmrs.org/openmrs/login.htm");
        loginPage.login(username,password,locationName);
        homePage.clickApplication(appName);
        List<String> headers=registerPage.getHeaders();
        List<WebElement> actualHeaders=registerPage.headers;
        Assert.assertEquals(actualHeaders.size(),headers.size());

        for(int i=0;i<registerPage.getHeaders().size();i++){
            String expectedHeader=headers.get(i);
            String actualHeader=actualHeaders.get(i).getText();
            Assert.assertEquals(actualHeader,expectedHeader);
        }
    }


    @Test(priority = 2, dependsOnMethods = "validateHeaders")
    public void validatePatientRegister(){

        registerPage.givenName.sendKeys("MM");
        registerPage.familyName.sendKeys("TT");
        registerPage.clickHeaders("Gender");
        Select select=new Select(registerPage.gender);
        select.selectByVisibleText("Male");
        registerPage.clickHeaders("Birthdate");
        registerPage.estimatedYears.sendKeys("22");
        registerPage.estimatedMonths.sendKeys("6");
        registerPage.clickHeaders("Address");
        registerPage.address1.sendKeys("2200 E Lincoln Ave");
        registerPage.city.sendKeys("Des Plaines");
        registerPage.state.sendKeys("IL");
        registerPage.country.sendKeys("USA");
        registerPage.zipCode.sendKeys("50332");
        registerPage.clickHeaders("Phone Number");
        registerPage.phoneNumber.sendKeys("4326547788");
        registerPage.clickHeaders("Relatives");

        registerPage.deleteRelation();
        select=new Select(registerPage.relationShipType);
        select.selectByVisibleText("Parent");
        registerPage.personName.sendKeys("Susan Adams");

        registerPage.clickHeaders("Confirm");

        List<WebElement> confirmationDetails=registerPage.confirmationInfo;

        Assert.assertEquals(confirmationDetails.get(0).getText(),"Name: MM, TT");
        Assert.assertEquals(confirmationDetails.get(1).getText(),"Gender: Male");
        Assert.assertEquals(confirmationDetails.get(2).getText(),"Birthdate: 22 year(s), 6 month(s)");
        Assert.assertEquals(confirmationDetails.get(3).getText(),"Address: 2200 E Lincoln Ave, Des Plaines, IL, USA, 50332");
        Assert.assertEquals(confirmationDetails.get(4).getText(),"Phone Number: 4326547788");
        Assert.assertEquals(confirmationDetails.get(5).getText(),"Relatives: Susan Adams - Parent");

        registerPage.confirmButton.submit();

    }


    @Test(priority = 3, dataProvider = "registerData", dataProviderClass = PatientRegisterData.class)
    public void validateMultipleRegister(String name, String lname, String gender, String year, String month,
                String address1, String city, String state,String country,String zipCode,
                String phone, String relationShipType,String personName) throws InterruptedException {

            registerPage.homeButton.click();
            homePage.clickApplication("Register a patient");

            registerPage.givenName.sendKeys(name);
            registerPage.familyName.sendKeys(lname);
            registerPage.clickHeaders("Gender");
            Select select=new Select(registerPage.gender);
        select.selectByVisibleText(gender);
        registerPage.clickHeaders("Birthdate");
        registerPage.estimatedYears.sendKeys(year);
        registerPage.estimatedMonths.sendKeys(month);
        registerPage.clickHeaders("Address");
        registerPage.address1.sendKeys(address1);
        registerPage.city.sendKeys(city);
        registerPage.state.sendKeys(state);
        registerPage.country.sendKeys(country);
        registerPage.zipCode.sendKeys(zipCode);
        registerPage.clickHeaders("Phone Number");
        registerPage.phoneNumber.sendKeys(phone);
        registerPage.clickHeaders("Relatives");
        Thread.sleep(1000);
        registerPage.deleteRelation();
        Thread.sleep(1000);
        select=new Select(registerPage.relationShipType);
        select.selectByVisibleText(relationShipType);
        registerPage.personName.sendKeys(personName);

        registerPage.clickHeaders("Confirm");

        List<WebElement> confirmationDetails=registerPage.confirmationInfo;

        Assert.assertEquals(confirmationDetails.get(0).getText(),"Name: "+name+", "+lname);
        Assert.assertEquals(confirmationDetails.get(1).getText(),"Gender: "+gender);
        Assert.assertEquals(confirmationDetails.get(2).getText(),"Birthdate: "+year+" year(s), "+month+" month(s)");
        Assert.assertEquals(confirmationDetails.get(3).getText(),"Address: "+address1+", "+city+", "+state+", "+country+", "+zipCode);
        Assert.assertEquals(confirmationDetails.get(4).getText(),"Phone Number: "+phone);
        Assert.assertEquals(confirmationDetails.get(5).getText(),"Relatives: "+personName+" - "+relationShipType);

        registerPage.confirmButton.submit();

    }






}
