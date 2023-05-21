import java.sql.*;

interface books{
    void insertBook(int Row,String Book,String Genre,int Price);

}
public class AddBooksDAO implements books {
    @Override
    public void insertBook(int Row, String Book, String Genre, int Price) {
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
            ptst.setString(2, Book);
            ptst.setString(3, Genre);
            ptst.setInt(4,Price);
            ptst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
