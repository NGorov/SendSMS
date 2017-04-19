import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;


public class CaptchaSolver {
    public static boolean isEmpty(BufferedImage Img, int ImgW, int ImgH) {
        Random rand = new Random();
        int clr;
        int red;
        int green;
        int blue;
        for (int i = 0; i < 100; i++) {
            clr = Img.getRGB(rand.nextInt(ImgW), rand.nextInt(ImgH));
            red = (clr & 0x00ff0000) >> 16;
            green = (clr & 0x0000ff00) >> 8;
            blue = clr & 0x000000ff;
            //System.out.printf("red green blue  = %s %s %s \n", red, green, blue);
            // иногда попадаются точки, которые в RGB не 255 255 255, а 223 223 223, поэтому:
            if ((red < 223) && (green < 223) && (blue < 223)) {
                return false;
            }
        }
        return true;
    }

    public static BufferedImage solveCaptcha(WebDriver driver, WebElement cap) throws InterruptedException, IOException {
        BufferedImage imgCap = null;
        Dimension capDimension = null;
        while (true) {
            //WebDriver augmentedDriver = new Augmenter().augment(driver);
            byte[] arrScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            BufferedImage imageScreen = ImageIO.read(new ByteArrayInputStream(arrScreen));
            //ImageIO.write(imageScreen, "png", fos);
            capDimension = cap.getSize();
            Point capLocation = cap.getLocation();
            imgCap = imageScreen.getSubimage(capLocation.x, capLocation.y, capDimension.width, capDimension.height);
            // проверяем, не пустое ли получено изображение (могло не успеть подгрузиться)
            if (!isEmpty(imgCap, capDimension.width, capDimension.height))
                break;
            Thread.sleep(500);
        }
        //ImageIO.write(imgCap, "png", fos);
        return imgCap;
    }
}
/* Проверка содержания изображения
    @Testing
    public void main() throws InterruptedException, IOException {
        File fos = new File("d://image.png");
        BufferedImage imgCap = ImageIO.read(fos);
        System.out.println(isEmpty(imgCap, 300, 57));

    }
    */