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

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(AddUsers.this,
                            "Please enter a value in all the fields.",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(AddUsers.this,
                            "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String hashedPassword = hashPassword(password);

                try {
                    int rows = 0;
                    boolean isAdmin = false;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
                    String query = "SELECT MAX(UID) AS total_rows FROM users";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        rows = resultSet.getInt("total_rows");
                    }
                    if (username.equals("admin")) {
                        isAdmin = true;
                    }
                    query = "SELECT * FROM users";
                    statement = conn.createStatement();
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next())
                    {
                        String User = resultSet.getString("User Name");
                        if(User.equals(username))
                        {
                            JOptionPane.showMessageDialog(AddUsers.this,"User Already Exists","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    query = "INSERT INTO users VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareCall(query);
                    preparedStatement.setInt(1, rows + 1);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, hashedPassword);
                    preparedStatement.setBoolean(4, isAdmin);
                    preparedStatement.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(AddUsers.this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddUsers.this, "An error occurred while adding the user.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private String hashPassword(String password) {
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
