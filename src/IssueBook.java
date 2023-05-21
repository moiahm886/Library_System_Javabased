import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IssueBook extends JFrame {
    private JLabel BookID;
    private JLabel UserID;
    private JLabel period;
    private JLabel IssuedDate;
    private JTextField BookIDText;
    private JTextField UserIDText;
    private JTextField periodText;
    private JTextField IssuedDateText;
    public IssueBook()
    {
        setTitle("Add Books");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        BookID = new JLabel("BookID");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(BookID, constraints);

        BookIDText = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(BookIDText, constraints);

        UserID = new JLabel("UserID");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(UserID, constraints);

        UserIDText = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(UserIDText, constraints);

        period = new JLabel("Period");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(period, constraints);

        periodText = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(periodText, constraints);

        IssuedDate = new JLabel("Issued Date");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(IssuedDate, constraints);

        IssuedDateText = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(IssuedDateText, constraints);

        JButton SubmitButton;
        SubmitButton = new JButton("Submit");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(SubmitButton, constraints);

        JButton CancelButton;
        CancelButton = new JButton("Cancel");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(CancelButton, constraints);

        add(panel);
        setVisible(true);

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Book = BookIDText.getText();
                String User = UserIDText.getText();
                String Period = periodText.getText();
                String IssuedDate = IssuedDateText.getText();
                IssueBookDAO IBD = new IssueBookDAO();
                IBD.bookIssue(Book,User,Period,IssuedDate);
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
