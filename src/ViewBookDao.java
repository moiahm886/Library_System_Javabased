import javax.swing.table.DefaultTableModel;
import java.sql.*;

interface BookDao {
   void getTable(DefaultTableModel table);
}
public class ViewBookDao implements BookDao {

   @Override
   public void getTable(DefaultTableModel table) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
         String SQL = "SELECT * FROM viewbooks";
         PreparedStatement ptst = conn.prepareStatement(SQL);
         ResultSet resultSet = ptst.executeQuery();

         while (resultSet.next()) {
            int bid = resultSet.getInt("BID");
            String bname = resultSet.getString("BNAME");
            String genre = resultSet.getString("GENRE");
            double price = resultSet.getDouble("PRICE");

            table.addRow(new Object[]{bid, bname, genre, price});
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
