/**
 * Created by ProVisaNP on 013 13.04.17.
 */

public class Main {
    static SendSMS smsSender;
    public static void main(String[] args) throws Exception {
        smsSender = new SendSMS();
        Thread myThready = new Thread(smsSender);	//Создание потока "myThready"
        myThready.start();
        TextWindow textWindow = new TextWindow();
        textWindow.setVisible(true);
        while (textWindow.phoneNumber == null) {
            Thread.sleep(50);
        }
        textWindow.setVisible(false);
        //smsSender.sendSms(textWindow.smsText, "375" + textWindow.phoneNumber);
    }
}
