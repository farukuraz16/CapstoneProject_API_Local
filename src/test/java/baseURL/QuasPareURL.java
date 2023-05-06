package baseURL;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ConfigReader;

public class QuasPareURL {
    protected RequestSpecification specification;
    String username = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");


    @Before
    public void setUpBaseURL(){

        specification = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseURL")).build();

    }
}
