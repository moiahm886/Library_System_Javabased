import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JButton ViewBook;
    private JButton ViewUser;
    private JButton ViewIssuedBooks;
    private JButton IssueBook;
    private JButton AddUser;
    private JButton AddBook;
    private JButton ReturnBook;
    private JButton CreateReset;

    public HomePage()
    {
        setTitle("Home Page");
        setSize(600, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        ViewBook = new JButton("View Books");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(ViewBook,constraints);

        ViewUser = new JButton("View Users");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(ViewUser,constraints);

        ViewIssuedBooks = new JButton("View Issued Books");
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(ViewIssuedBooks,constraints);

        IssueBook = new JButton("Issue Books");
        constraints.gridx = 3;
        constraints.gridy = 0;
        panel.add(IssueBook,constraints);


        AddUser = new JButton("Add Users");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(AddUser,constraints);

        AddBook = new JButton("Add Books");
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(AddBook,constraints);

        ReturnBook = new JButton("     Return Books    ");
        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(ReturnBook,constraints);

        CreateReset = new JButton("Create/Reset");
        constraints.gridx = 3;
        constraints.gridy = 1;
        panel.add(CreateReset,constraints);

        add(panel);

        ViewBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBooks VB = new ViewBooks();
                VB.setVisible(true);
            }
        });
        AddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBooks AB = new AddBooks();
                AB.setVisible(true);
            }
        });
        AddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUsers AU = new AddUsers();
                AU.setVisible(true);
            }
        });
        ViewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewUsers VU = new ViewUsers();
                VU.setVisible(true);
            }
        });
        CreateReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetDatabase RD = new ResetDatabase();
                RD.setVisible(true);
            }
        });

        IssueBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IssueBook IB = new IssueBook();
                IB.setVisible(true);
            }
        });
        ViewIssuedBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewIssuedBooks VIB = new ViewIssuedBooks();
                VIB.setVisible(true);
            }
        });
        ReturnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnBook RB = new ReturnBook();
                RB.setVisible(true);
            }
        });
    }

}
