package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private static WebDriver driver;

    private Driver(){}

    public static WebDriver getDriver(String driverName) {

        if(driver==null){
            switch (driverName){
                case "chrome":
                    try {
                        String nodeUrl = "http://18.219.122.197:5555/wd/hub";
                        // chromeDriver path in remote desktop
                        File file = new File("C:\\Users\\Administrator\\Downloads\\chromedriver.exe");
                        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                        capabilities.setBrowserName("chrome");
                        capabilities.setPlatform(Platform.ANY);
                        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
                    } catch (MalformedURLException e) {
                        System.out.println("Node URL is not correct!");
                        System.out.println(e.getStackTrace());
                    }
                    break;

                case "chrome1":
                    try {
                        String nodeUrl = "http://18.219.122.197:5555/wd/hub";
                        // chromeDriver path in remote desktop
                        File file = new File("C:\\Users\\Administrator\\Downloads\\chromedriver.exe");
                        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                        capabilities.setBrowserName("chrome");
                        capabilities.setPlatform(Platform.ANY);
                        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
                    } catch (MalformedURLException e) {
                        System.out.println("Node URL is not correct!");
                        System.out.println(e.getStackTrace());
                    }
                    break;

                case "firefox":
                    try {
                        String nodeUrl = "http://18.219.122.197:5555/wd/hub";
                        // chromeDriver path in remote desktop
                        File file = new File("C:\\Users\\Administrator\\Downloads\\geckodriver.exe");
                        System.setProperty("webdriver.geckodriver.driver", file.getAbsolutePath());
                        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                        capabilities.setBrowserName("firefox");
                        capabilities.setPlatform(Platform.ANY);
                        driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
                    } catch (MalformedURLException e) {
                        System.out.println("Node URL is not correct!");
                        System.out.println(e.getStackTrace());
                    }
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
            }
        }
        driver.manage().window().maximize();

        return driver;
    }

}
