import com.mysql.cj.xdevapi.AddStatement;

import javax.swing.*;
import java.sql.*;

interface user{
    void insertUser(String username, String password,String confirmPassword);
}
public class AddUsersDAO implements user {
    @Override
    public void insertUser(String username, String password,String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a value in all the fields.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null,
                    "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        AddUsers AU = new AddUsers();
        String hashedPassword = AU.hashPassword(password);

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
                    JOptionPane.showMessageDialog(null,"User Already Exists","Error",JOptionPane.ERROR_MESSAGE);
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

            JOptionPane.showMessageDialog(null, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while adding the user.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
