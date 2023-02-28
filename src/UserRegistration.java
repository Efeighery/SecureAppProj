import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistration extends JDialog{
    private JTextField nameTF;
    private JTextField phoneNoTF;
    private JTextField addressTF;
    private JTextField emailAdTF;
    private JTextField passwordTF;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JTextField confirmPWTF;
    private JPanel registrationPage;

    public UserRegistration(JFrame jFrame){
        super(jFrame);
        setTitle("Make a new account here!");
        setContentPane(registrationPage);

        setMinimumSize(new Dimension(600, 575));
        setModal(true);
        setLocationRelativeTo(jFrame);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regUserAccount();
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFunction();
            }
        });

        setVisible(true);
    }

    private void exitFunction() {
    }

    private void regUserAccount() {
        String uName = nameTF.getText();
        String uEmail = emailAdTF.getText();
        String uPhone = phoneNoTF.getText();
        String uAddress = addressTF.getText();
        String uPassword = passwordTF.getText();
        String confirmUserPassword = confirmPWTF.getText();
    }

    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration(null);
    }

}
