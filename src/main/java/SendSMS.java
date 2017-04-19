import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SendSMS implements Runnable {
    private WebDriver driver;
    static String Smstext;
    static String phoneNumber;
    public void run() {
        String clipboardTxt;
        String clipboardTxtNew;
        // создаем окно браузера
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Program Files\\Java\\jdk1.8.0_77\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().deleteAllCookies();

        /*
        System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Java\\jdk1.8.0_77\\bin\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().deleteAllCookies();*/
        try {
            System.out.printf("\nsending message " + Smstext + " to " + phoneNumber + "\n");
            try {
                // открываем страницу
                do {
                    String captchaByUser;
                    WebElement captchaResult;
                    driver.navigate().to("https://ihelper.mts.by/selfcare/");
                    /*
                    WebElement privateCabinet = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("cabinet")));
                    privateCabinet.click();
                    WebElement privateCabinetPopup = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("cabinet_popup_link")));
                    privateCabinetPopup.click(); */
                    WebElement loginPhoneNumber = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_tbPhoneNumber")));
                    loginPhoneNumber.clear();
                    loginPhoneNumber.sendKeys("333333324");
                    WebElement loginPassword = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_tbPassword")));
                    loginPassword.clear();
                    loginPassword.sendKeys("1986198");
                    WebElement captchaImg;
                    try {
                        captchaImg = (new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.id("captcha")));
                        //captchaByUser = CaptchaSolver.solveCaptcha(driver, captchaImg); // передать капчу на ввод юзеру
                        // получаем поле ввода капчи
                        //captchaResult = (new WebDriverWait(driver, 120)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_captchaBox")));
                        //captchaResult.sendKeys(captchaByUser);
                    } catch (Exception e) {

                    }
                    WebElement submitButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_btnEnter")));
                    submitButton.click();
                    try {
                        WebElement customerBlock = (new WebDriverWait(driver, 25)).until(ExpectedConditions.presenceOfElementLocated(By.className("customer-info")));
                        if (customerBlock.isDisplayed())
                            break;
                    } catch (Throwable e) {
                        continue;
                    }
                } while (true);
                driver.navigate().to("https://ihelper.mts.by/selfcare/service-sms.aspx");
                //WebElement smsSendLink = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("ОТПРАВКА SMS")));
                //smsSendLink.click();
                WebElement smsPhoneNumber = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_controlSms_telephoneNumber")));
                smsPhoneNumber.sendKeys(phoneNumber);
                WebElement smsTextToSend = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_controlSms_messageTextBox")));
                smsTextToSend.sendKeys(Smstext);
                WebElement submitButton2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_btnSend")));
                submitButton2.click();
                WebElement statusTextBox;
                Integer countI = 0;
                do {
                    Thread.sleep(3000);
                    statusTextBox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_messageStatusTextBox")));
                    try {
                        WebElement checkStatusButton = (new WebDriverWait(driver, 1)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_CheckStatusButton")));
                        checkStatusButton.click();
                    } catch (Exception e) {
                        break;
                    }
                    statusTextBox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_MainContent_messageStatusTextBox")));
                    countI++;
                } while ((countI<=10)&&(!statusTextBox.getText().equals("Доставлено")));
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, statusTextBox.getText(), "ProVisa SMS",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                driver.quit();
            } finally {
            }
        } catch (Throwable tx) {
            tx.printStackTrace();
            System.out.printf("unable to send sms, error %s \n", tx.getMessage());
        }
    }
}
