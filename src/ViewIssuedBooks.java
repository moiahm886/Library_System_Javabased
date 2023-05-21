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
        ViewIssueBookDAO VIBD = new ViewIssueBookDAO();
        VIBD.getTable(tableModel);
    }
}
