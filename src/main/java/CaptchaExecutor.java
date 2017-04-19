import java.awt.image.BufferedImage;

public class CaptchaExecutor {
    public static String executeCaptcha(BufferedImage bufferedImage) throws InterruptedException {
        ImageImplement imageImplement = new ImageImplement(bufferedImage);
        imageImplement.setVisible(true);
        while (imageImplement.captcha == null) {
            Thread.sleep(50);
        }
        imageImplement.setVisible(false);
        return imageImplement.captcha;
    }
}