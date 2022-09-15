package com.sakila.api.SakilaApp.BDD;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FrontEndAutomation {

    private FirefoxOptions options;
    private WebDriver driver;

    private String driverPath;
    private String webPath;

    String expected;
    String actual;
    int initialValue;

    public FrontEndAutomation(){
        this.webPath = "http://localhost:3000";
        this.driverPath = "D:\\tsi\\Sakilla\\sakila-shenanigans\\geckodriver-v0.31.0-win64\\geckodriver.exe";

        System.setProperty("webdriver.gecko.driver", driverPath);
        this.options = new FirefoxOptions();
    }

    @Given("I am using a modern browser")
    public void i_am_using_modern_browser() {
        driver = new FirefoxDriver(options);
    }

    @When("I load the index page")
    public void load_index_page() {
        driver.get(webPath);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }

    @Then("The project logo is displayed")
    public void check_logo_displayed() {
        expected = "Imaginary Movie Database";
        actual = driver.findElement(By.id("logo")).getText();

        Assertions.assertEquals(expected, actual, "Incorrect project name");
        driver.close();
    }

    @When("I load the browse movie page")
    public void load_browse_movie() {
        driver.get(webPath + "/browse/1000");
    }

    @When("I send an {string} rating")
    public void send_rating(String emotion) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emotion+"Number")));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(50000));
        initialValue = Integer.parseInt(driver.findElement(By.id(emotion+"Number")).getText());
        System.out.println(initialValue);

        WebElement button = driver.findElement(By.id(emotion+"Button"));
        button.click();
    }

    @Then("The {string} ratings are increased by one")
    public void check_rating_increase(String emotion) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'go')]")));
        int finalValue = Integer.parseInt(driver.findElement(By.id(emotion+"Number")).getText());
        Assertions.assertEquals(initialValue + 1, finalValue, "Values mismatch");
        driver.close();
    }
}
