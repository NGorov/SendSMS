import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class TextWindow extends JFrame {
    private JLabel jImage;
    private JLabel jLabel;
    private JTextField jTextField;
    private JTextArea jTextArea;
    private JButton jButton;
    private JList jList;
    private JScrollPane jScrollPane;
    private String[] data = {"29", "33", "44", "25"};

    public String smsText = "Компания ПроВиза, Ул. Я. Коласа, д. 37. офис 50. www.provisa.by e-mail: info@provisa.by с 9-19:00 без перерыва на обед. Тел. Мтс 771-0000, Велком 334-0000. Благодарим Вас!";
    public String phoneNumber = null;
    public String phoneCode = null;

    public TextWindow() {
        super();
        this.setSize(325, 320);
        this.getContentPane().setLayout(null);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setTitle("ProVisa Send SMS");
        this.add(getJLabel(), null);
        this.add(getJTextField(), null);
        this.add(getJTextArea(), null);
        jButton = getJButton();
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                phoneNumber = jTextField.getText();
                smsText = jTextArea.getText();
            }
        });
        this.add(jButton, null);
        jList = getjList(data);
        jScrollPane = getjScrollPane(jList);
        this.add(jScrollPane, null);
        this.add(jList, null);
        this.requestFocus();
    }

    public void addImage(BufferedImage bufferedImage) {
        this.add(getJImg(bufferedImage));
        this.revalidate();
        validate();
    }

    private JLabel getJLabel() {
        if (jLabel == null) {
            jLabel = new JLabel();
            jLabel.setBounds(14, 10, 53, 18);
            jLabel.setText("+375");
        }
        return jLabel;
    }

    private javax.swing.JLabel getJImg(BufferedImage bufferedImage) {
        if (jImage == null) {
            ImageIcon image = new ImageIcon(bufferedImage);
            jImage = new javax.swing.JLabel(image);
            jImage.setBounds(10, 30, 100, 60);
            jImage.setIcon(image);
        }
        return jImage;
    }

    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setBounds(86, 10, 160, 20);

            jTextField.addKeyListener
                    (new KeyAdapter() {
                         public void keyPressed(KeyEvent e) {
                             int key = e.getKeyCode();
                             if (key == KeyEvent.VK_ENTER) {
                                 phoneNumber = jTextField.getText();
                             }
                         }
                     }
                    );
        }

        return jTextField;
    }

    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
            jTextArea.setBounds(17, 90, 275, 140);
            jTextArea.setLineWrap(true);
            jTextArea.setWrapStyleWord(true);
            jTextArea.setText(smsText);
            jTextArea.addKeyListener
                    (new KeyAdapter() {
                         public void keyPressed(KeyEvent e) {
                             int key = e.getKeyCode();
                             if (key == KeyEvent.VK_ENTER) {
                                 smsText = jTextArea.getText();
                             }
                         }
                     }
                    );
        }

        return jTextArea;
    }

    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(103, 240, 101, 27);
            jButton.setText("Ok");
        }
        return jButton;
    }

    private JList getjList(String[] data) {
        if (jList == null) {
            jList = new JList(data);
            jList.setBounds(50, 10, 30, 20);
        }
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.addListSelectionListener(new SelectionHandler());
        return jList;
    }

    private class SelectionHandler implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                phoneCode = Arrays.toString(jList.getSelectedValues());
            }
        }
    }

    private JScrollPane getjScrollPane(JList jList) {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane(jList);
            jScrollPane.setPreferredSize(new Dimension(20, 50));
        }
        return jScrollPane;
    }

    public String getCaptcha() {
        return smsText;
    }
}

