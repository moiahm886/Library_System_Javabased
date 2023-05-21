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
        ViewUserDAO VUD = new ViewUserDAO();
        VUD.getTable(tableModel);
    }
}
