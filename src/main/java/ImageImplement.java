import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ImageImplement extends JFrame {
    private JLabel jImage;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton jButton;

    public String captcha = null;

    public ImageImplement(BufferedImage bufferedImage) {
        super();
        this.setSize(325, 200);
        this.getContentPane().setLayout(null);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.add(getJImg(bufferedImage), null);
        this.add(getJLabel(), null);
        this.add(getJTextField(), null);
        jButton = getJButton();
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                captcha = jTextField.getText();
            }
        });
        this.add(jButton, null);
        this.setTitle("Enter Captcha");
        this.requestFocus();

    }

    private JLabel getJLabel() {
        if (jLabel == null) {
            jLabel = new JLabel();
            jLabel.setBounds(34, 79, 53, 18);
            jLabel.setText("Captcha:");
        }
        return jLabel;
    }

    private JLabel getJImg(BufferedImage bufferedImage) {
        if (jImage == null) {
            ImageIcon image = new ImageIcon(bufferedImage);
            jImage = new JLabel(image);
            jImage.setBounds(0, 0, 310, 60);
            jImage.setIcon(image);
        }
        return jImage;
    }


    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setBounds(86, 79, 160, 20);

            jTextField.addKeyListener
                    (new KeyAdapter() {
                         public void keyPressed(KeyEvent e) {
                             int key = e.getKeyCode();
                             if (key == KeyEvent.VK_ENTER) {
                                 captcha = jTextField.getText();
                             }
                         }
                     }
                    );
        }

        return jTextField;
    }

    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(103, 110, 101, 27);
            jButton.setText("OK");
        }
        return jButton;
    }

    public String getCaptcha() {
        return captcha;
    }
}

