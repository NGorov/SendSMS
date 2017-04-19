import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by ProVisaNP on 017 17.04.17.
 */
public class Testing {
    public static void main(String[] args) throws Exception {
        try {
           /* System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\jdk1.8.0_77\\bin\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            driver.manage().deleteAllCookies();
            driver.navigate().to("https://ihelper.mts.by/selfcare/");
            WebElement captchaImg;
            captchaImg = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.id("captcha")));
            */
            TextWindow textWindow = new TextWindow();
            textWindow.setVisible(true);
            while (textWindow.phoneNumber == null) {
                Thread.sleep(50);
            }
            System.out.println(textWindow.phoneNumber + " " + textWindow.phoneCode + " " + textWindow.smsText );
            //textWindow.setVisible(false);
            //textWindow.addImage(CaptchaSolver.solveCaptcha(driver, captchaImg));
            //textWindow.setVisible(true);
            //textWindow.addImage(CaptchaSolver.solveCaptcha(driver, captchaImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
