import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetDatabase extends JFrame {
    private JButton resetViewBook;
    private JButton resetViewUser;
    private JButton resetIssuedBooks;
    private int choice;

    public ResetDatabase()
    {
        setTitle("Home Page");
        setSize(600, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        resetViewBook = new JButton("Reset View Books");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(resetViewBook,constraints);

        resetViewUser = new JButton("Reset View Users");
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(resetViewUser,constraints);

        resetIssuedBooks = new JButton("Reset Issued Books");
        constraints.gridx = 3;
        constraints.gridy = 0;
        panel.add(resetIssuedBooks,constraints);

        add(panel);
        ResetDatabaseDAO Reset = new ResetDatabaseDAO();
        resetViewBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation();
                if(choice==JOptionPane.YES_OPTION) {
                    Reset.resetBooks();
                }
            }
        });
        resetViewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation();
                if(choice==JOptionPane.YES_OPTION) {
                    Reset.resetUsers();
                }
            }
        });
        resetIssuedBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation();
                if(choice==JOptionPane.YES_OPTION) {
                    Reset.issued();
                }
            }
        });

    }
    public void confirmation()
    {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the database?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(choice==JOptionPane.NO_OPTION)
            {
                Window window = SwingUtilities.getWindowAncestor(JOptionPane.getRootFrame());
                if (window != null) {
                    window.dispose();
                }
            }
    }
}
