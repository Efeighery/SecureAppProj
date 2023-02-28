import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
        String uPassword = String.valueOf(passwordTF.getText());
        String confirmUserPassword = String.valueOf(confirmPWTF.getText());

        if(uName.isEmpty() || uEmail.isEmpty() || uPhone.isEmpty() || uAddress.isEmpty() || uPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "All fields need to be filled",
                    "Please try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!uPassword.equals(confirmUserPassword)) {
            JOptionPane.showMessageDialog(this, "Password doesn't match", "Please try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        userAcc = addToDB(uName, uEmail, uPhone, uAddress, uPassword);

        if(userAcc != null){
            exitFunction();
        }
        else{
            JOptionPane.showMessageDialog(this, "Couldn't register user account", "Try again", JOptionPane.ERROR_MESSAGE);

        }
    }

    public UserAcc userAcc;
    private UserAcc addToDB(String uName, String uEmail, String uPhone, String uAddress, String uPassword) {
        UserAcc userAcc = null;

        final String DB_URL = "https://demo.phpmyadmin.net/master-config/public/index.php?route=/database/structure&db=USERS";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement states = con.createStatement();
            String sqlOp = "INSERT INTO USERS (uName, uEmail, uPhone, uAddress, uPassword)"+ "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepStates = con.prepareStatement(sqlOp);

            prepStates.setString(1, uName);
            prepStates.setString(2, uEmail);
            prepStates.setString(3, uPhone);
            prepStates.setString(4, uAddress);
            prepStates.setString(5, uPassword);

            int rowIncrementer = prepStates.executeUpdate();

            if(rowIncrementer > 0){
                userAcc = new UserAcc();
                userAcc.uName = uName;
                userAcc.uEmail = uEmail;
                userAcc.uPhone = uPhone;
                userAcc.uAddress = uAddress;
                userAcc.uPassword = uPassword;
            }

            states.close();

            con.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return userAcc;

    }

    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration(null);

        UserAcc userAcc = userRegistration.userAcc;

        if(userAcc != null){
            System.out.println("Registered account of: "+userAcc.uName);
        }
        else{
            System.out.println("Cancelled account registration");
        }
    }

}
