import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewIssuedBooks extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewIssuedBooks() {
        setTitle("View Books");
        setPreferredSize(new Dimension(800, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("BID");
        tableModel.addColumn("UID");
        tableModel.addColumn("Period");
        tableModel.addColumn("IssuedDate");
        tableModel.addColumn("Book Name");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Price");

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setVisible(true);

        fetchData();
    }

    public void fetchData() {
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
