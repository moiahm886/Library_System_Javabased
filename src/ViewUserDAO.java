import javax.swing.table.DefaultTableModel;
import java.sql.*;

interface users{
    void getTable(DefaultTableModel table);
}
public class ViewUserDAO implements users {
    @Override
    public void getTable(DefaultTableModel table) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            String SQL = "SELECT * FROM users";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet resultSet = ptst.executeQuery();

            while (resultSet.next()) {
                int uid = resultSet.getInt("UID");
                String UserName = resultSet.getString("User Name");
                boolean Admin = resultSet.getBoolean("Admin");

                table.addRow(new Object[]{uid, UserName, Admin});
            }

            resultSet.close();
            ptst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
