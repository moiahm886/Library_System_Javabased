import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

interface returnBK{
    void getTable(DefaultTableModel tableModel);
    void Return(JTable table,DefaultTableModel tableModel);
    void RowAdd(int bid,String BookName, String Genre, Double Price);
}
public class ReturnBookDAO implements returnBK {
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

                tableModel.addRow(new Object[]{bid, UID, period, DateIssued, bName, genre, price});
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

    @Override
    public void Return(JTable table,DefaultTableModel tableModel) {
        ReturnBook RB = new ReturnBook();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int bid = (int) table.getValueAt(selectedRow, 0);
            String BookName = (String) table.getValueAt(selectedRow,4);
            String Genre = (String) table.getValueAt(selectedRow,5);
            Double Price = (Double) table.getValueAt(selectedRow,6);
            RB.addRow(bid,BookName,Genre,Price);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Book with BID: " + bid + " returned and deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to return the book.");
        }
    }

    @Override
    public void RowAdd(int bid, String BookName, String Genre, Double Price) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
            String SQL = "DELETE FROM issuedbooks WHERE BID = "+ bid;
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ptst.executeUpdate();
            SQL = "insert into viewbooks values(?,?,?,?)";
            ptst = conn.prepareCall(SQL);
            ptst.setInt(1,bid);
            ptst.setString(2, BookName);
            ptst.setString(3, Genre);
            ptst.setDouble(4,  Price);
            ptst.executeUpdate();
            ptst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
