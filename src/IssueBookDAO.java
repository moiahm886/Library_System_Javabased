import javax.swing.*;
import java.sql.*;

interface issue{
    void bookIssue(String Book, String User,String Period,String IssuedDate);
}
public class IssueBookDAO implements issue{
    @Override
    public void bookIssue(String Book, String User, String Period, String IssuedDate) {
        if (Book.isEmpty() || User.isEmpty() ||
                Period.isEmpty() || IssuedDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
            return;
        }
        try{
            boolean found = false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","");
            String SQL = "SELECT * FROM viewbooks";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            int bookId = Integer.parseInt(Book);
            while(resultSet.next())
            {
                int bid = resultSet.getInt("BID");
                if(bid==bookId){
                    found =true;
                    String BookName = resultSet.getString(2);
                    String Genre = resultSet.getString(3);
                    Double price = resultSet.getDouble(4);
                    SQL = "insert into issuedbooks values(?,?,?,?,?,?,?)";
                    PreparedStatement ptst = conn.prepareCall(SQL);
                    ptst.setInt(1,bid);
                    ptst.setInt(2,Integer.parseInt(User));
                    ptst.setInt(3,Integer.parseInt(Period));
                    ptst.setString(4,IssuedDate);
                    ptst.setString(5,BookName);
                    ptst.setString(6,Genre);
                    ptst.setDouble(7,price);
                    ptst.executeUpdate();
                    SQL = "DELETE FROM viewbooks WHERE BID = " + bid;
                    ptst = conn.prepareCall(SQL);
                    ptst.executeUpdate();
                }
            }
            if(!found)
            {
                JOptionPane.showMessageDialog(null,"Book you want to issue not found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
