import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AddUsers extends JFrame {
    public AddUsers() {
        setTitle("Add Users");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel username = new JLabel("User Name");
        JTextField userNameText = new JTextField(10);
        JLabel pwd = new JLabel("Password");
        JPasswordField pwdText = new JPasswordField(10);
        JLabel pwdConfirm = new JLabel("Confirm Password");
        JPasswordField pwdConfirmText = new JPasswordField(10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(username, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userNameText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(pwd, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(pwdText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(pwdConfirm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(pwdConfirmText, constraints);

        JButton addUserButton = new JButton("Add User");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(addUserButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(cancelButton, constraints);

        add(panel);
        setVisible(true);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameText.getText();
                String password = String.valueOf(pwdText.getPassword());
                String confirmPassword = String.valueOf(pwdConfirmText.getPassword());
                AddUsersDAO AUD = new AddUsersDAO();
                AUD.insertUser(username,password,confirmPassword);
                dispose();
                userNameText.setText("");
                pwdText.setText("");
                pwdConfirmText.setText("");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
