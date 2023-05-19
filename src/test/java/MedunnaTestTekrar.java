import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MedunnaTestTekrar {
       /*
 Given
   User connects to the database
   (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

 When
   User sends the query to get the created room
   (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

 Then
   Assert that room is created properly
   (Odanın düzgün kaydedildiğini doğrular)

 And
   User closes the connection
*/
    @Test
    public void medunnaTestTekrar() throws SQLException {

    //User connects to the database
    //(Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))
       JdbcUtils.connectToMedunnaDataBase();
       JdbcUtils.createStatement();

    //User sends the query to get the created room
    //(Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)
    String sql2 = "select * from room WHERE room_number = 35353535";
        ResultSet resultSet = JdbcUtils.executeQuery(sql2);

    // Assert that room is created properly
    // (Odanın düzgün kaydedildiğini doğrular)
        resultSet.next();
        assertEquals("123.00", resultSet.getString("price"));
        assertEquals("JDBC ilk testimiz için ", resultSet.getString(6));
        assertEquals("mark_twain", resultSet.getString("created_by"));

    //User closes the connection
        JdbcUtils.closeConnection();








    }


}
