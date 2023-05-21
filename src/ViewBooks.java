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
       ViewBookDao VBD = new ViewBookDao();
       VBD.getTable(tableModel);
    }

}
