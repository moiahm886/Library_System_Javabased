import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddBooks extends JFrame {
    private JLabel bookName;
    private JTextField book;
    private JLabel genreName;
    private JTextField genre;
    private JLabel priceLbl;
    private JTextField price;

    public AddBooks() {
        setTitle("Add Books");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        bookName = new JLabel("Book Name");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(bookName, constraints);

        book = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(book, constraints);

        genreName = new JLabel("Genre");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(genreName, constraints);

        genre = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(genre, constraints);

        priceLbl = new JLabel("Price");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(priceLbl, constraints);

        price = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(price, constraints);

        JButton SubmitButton;
        SubmitButton = new JButton("Submit");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(SubmitButton, constraints);

        JButton CancelButton;
        CancelButton = new JButton("Cancel");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(CancelButton, constraints);

        add(panel);
        setVisible(true);

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Books = book.getText();
                String Genre = genre.getText();
                int Price = Integer.parseInt(price.getText());

                if (book.getText().isEmpty() || genre.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AddBooks.this, "Please fill in all the fields.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int Row=0;
                AddBooksDAO ABD = new AddBooksDAO();
                ABD.insertBook(Row,Books,Genre,Price);
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
