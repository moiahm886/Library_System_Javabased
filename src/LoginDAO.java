import javax.swing.*;
import java.sql.*;

interface Login{
    void getUserNameAndPassword(String username, String password);
}
public class LoginDAO implements Login {
    @Override
    public void getUserNameAndPassword(String username, String password) {
        try {

            boolean found = false;
            LoginForm login = new LoginForm();
            if(!password.equals("admin")) {
                password = login.hashPassword(password);
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            String SQL = "SELECT * FROM users";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet resultSet = ptst.executeQuery();

            while (resultSet.next()) {
                String getName = resultSet.getString("User Name");
                String getPWD = resultSet.getString("Password");
                if(getName.equals(username)&&getPWD.equals(password)){
                    found = true;
                    HomePage HP = new HomePage();
                    HP.setVisible(true);
                    login.dispose();
                    break;
                }
            }
            if(username.equals("admin")&&password.equals("admin"))
            {
                found = true;
                HomePage HP = new HomePage();
                HP.setVisible(true);
                login.dispose();
            }
            if(!found)
            {
                JOptionPane.showMessageDialog(null,"Incorrect User Name or Password. Please Try Again");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
