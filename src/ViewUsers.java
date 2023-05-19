import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewUsers extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewUsers() {
        setTitle("View Users");
        setPreferredSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("UID");
        tableModel.addColumn("User Name");
        tableModel.addColumn("Admin");

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
            String SQL = "SELECT * FROM users";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet resultSet = ptst.executeQuery();

            while (resultSet.next()) {
                int uid = resultSet.getInt("UID");
                String UserName = resultSet.getString("User Name");
                boolean Admin = resultSet.getBoolean("Admin");

                tableModel.addRow(new Object[]{uid, UserName, Admin});
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
