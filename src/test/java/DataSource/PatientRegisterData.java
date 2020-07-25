package DataSource;

import org.testng.annotations.DataProvider;

public class PatientRegisterData {

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData(){

        return new Object[][]{
                {"Jackson","Jack","Male","21","3","223 dd ave","Des","IL","US","56789","3345568877","Parent","Mark Lewis"},
                {"John","Deer","Male","33","5","123 dd ave","CHI","IL","USA","60000","3345562222","Sibling","Mark Miller"},
                {"Jessica","King","Female","26","8","111 dd ave","Los Angeles","CA","USA","60668","3345561111","Supervisor","Betty Martinez"},
                {"Melissa","Lee","Female","24","9","888 dd ave","San Diego","CA","US","78765","3345560000","Child","Daniel Thomas"}
        };
    }
}
