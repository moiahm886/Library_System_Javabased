import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooks extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewBooks() {
        setTitle("View Books");
        setPreferredSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("BID");
        tableModel.addColumn("BNAME");
        tableModel.addColumn("GENRE");
        tableModel.addColumn("PRICE");

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
            String SQL = "SELECT * FROM viewbooks";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet resultSet = ptst.executeQuery();

            while (resultSet.next()) {
                int bid = resultSet.getInt("BID");
                String bname = resultSet.getString("BNAME");
                String genre = resultSet.getString("GENRE");
                double price = resultSet.getDouble("PRICE");

                tableModel.addRow(new Object[]{bid, bname, genre, price});
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
