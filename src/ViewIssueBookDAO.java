import javax.swing.table.DefaultTableModel;
import java.sql.*;

interface issueBookDAO{
    void getTable(DefaultTableModel tableModel);
}
public class ViewIssueBookDAO implements issueBookDAO {
    @Override
    public void getTable(DefaultTableModel tableModel) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            String SQL = "SELECT * FROM issuedbooks";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet resultSet = ptst.executeQuery();

            while (resultSet.next()) {
                int UID = resultSet.getInt("UID");
                int bid = resultSet.getInt("BID");
                String bName = resultSet.getString("Book Name");
                String genre = resultSet.getString("Genre");
                double price = resultSet.getDouble("Price");
                int period = resultSet.getInt("Period");
                String DateIssued = resultSet.getString("Date Issued");

                tableModel.addRow(new Object[]{bid,UID,period,DateIssued, bName, genre, price});
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
