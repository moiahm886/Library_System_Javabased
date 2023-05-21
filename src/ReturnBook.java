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
                ReturnBookDAO RBD = new ReturnBookDAO();
                RBD.Return(table,tableModel);
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
        ReturnBookDAO RBD = new ReturnBookDAO();
        RBD.getTable(tableModel);
    }

    public void addRow(int bid,String BookName, String Genre, Double Price) {
        ReturnBookDAO RBD = new ReturnBookDAO();
        RBD.RowAdd(bid,BookName,Genre,Price);
    }
}
