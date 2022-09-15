package com.sakila.api.SakilaApp.scraper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class MainSelenium {


    public static void main(String[] args) throws IOException, InterruptedException {

        // Arraylist of all un-downloaded items.
        // The script fails every 10 or so items, so the items are deleted from this list to make then not re-download.
        ArrayList<String> titles = new ArrayList<String>(Arrays.asList(
                "WIZARD COLDBLOODED",
                "WOLVES DESIRE",
                "WOMEN DORADO",
                "WON DARES",
                "WONDERFUL DROP",
                "WONDERLAND CHRISTMAS",
                "WONKA SEA",
                "WORDS HUNTER",
                "WORKER TARZAN",
                "WORKING MICROCOSMOS",
                "WORLD LEATHERNECKS",
                "WORST BANGER",
                "WRATH MILE",
                "WRONG BEHAVIOR",
                "WYOMING STORM",
                "YENTL IDAHO",
                "YOUNG LANGUAGE",
                "YOUTH KICK",
                "ZHIVAGO CORE",
                "ZOOLANDER FICTION",
                "ZORRO ARK"
        ));


        System.setProperty("webdriver.gecko.driver", "D:\\tsi\\Sakilla\\sakila-shenanigans\\geckodriver-v0.31.0-win64\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver;

        driver = new FirefoxDriver(options);

        for (String title : titles) {

            driver.get("https://app.wombo.art");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            WebElement textBox = driver.findElement(By.tagName("input"));

            textBox.sendKeys(title);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

            WebElement artStyle = driver.findElement(By.xpath("//img[@alt='Realistic']"));
            artStyle.click();

            WebElement submit = driver.findElement(By.xpath("//button[text()='Create']"));
            submit.click();

            // ShoutOut to TOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='ArtCard__CardImage-sc-67t09v-2 dOXnUm']")));

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

            WebElement imageElement = driver.findElement(By.xpath("//*[@class='ArtCard__CardImage-sc-67t09v-2 dOXnUm']"));
            String src = imageElement.getAttribute("src");

            BufferedImage image = ImageIO.read(new URL(src));
            File outputFile = new File("cards/" + title + ".png");
            ImageIO.write(image, "png", outputFile);

        }

    }
}
