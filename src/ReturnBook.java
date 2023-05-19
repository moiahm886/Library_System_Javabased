import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReturnBook extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton returnButton;

    public ReturnBook() {
        setTitle("View Books");
        setPreferredSize(new Dimension(800, 500));
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

        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int bid = (int) table.getValueAt(selectedRow, 0);
                    String BookName = (String) table.getValueAt(selectedRow,4);
                    String Genre = (String) table.getValueAt(selectedRow,5);
                    Double Price = (Double) table.getValueAt(selectedRow,6);
                    deleteRow(bid,BookName,Genre,Price);
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(ReturnBook.this, "Book with BID: " + bid + " returned and deleted.");
                } else {
                    JOptionPane.showMessageDialog(ReturnBook.this, "Please select a row to return the book.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(returnButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(contentPanel);
        setVisible(true);
        pack();
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

    public void deleteRow(int bid,String BookName, String Genre, Double Price) {
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
