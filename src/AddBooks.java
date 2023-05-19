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
                if (book.getText().isEmpty() || genre.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AddBooks.this, "Please fill in all the fields.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    int Rows = 0;
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","");
                    String SQL = "SELECT COUNT(*) AS total_rows FROM viewbooks";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(SQL);
                    if (resultSet.next()) {
                        Rows = resultSet.getInt("total_rows");
                    }
                    SQL = "insert into viewbooks values(?,?,?,?)";
                    PreparedStatement ptst = conn.prepareCall(SQL);
                    ptst.setInt(1,Rows+1);
                    ptst.setString(2, book.getText());
                    ptst.setString(3, genre.getText());
                    ptst.setInt(4,Integer.parseInt(price.getText()));
                    ptst.executeUpdate();
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
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
