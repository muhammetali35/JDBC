import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {
    /*
Given
  User connects to the database
  (Kullanıcı veritabanına bağlanır)

When
  User sends the query to get the region ids from "countries" table
  (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

Then
  Assert that the number of region ids greater than 1 is 17.
  (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

And
  User closes the connection
*/
    @Test
    public void countryTest() throws SQLException {// Test methodunda public olur . Hep void olur ve parametresi olmaz.
        //User connects to the database
        JdbcUtils.connectToDataBase();// JdbcUtils classından method çağırarak  bağlandık
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql = "select count (region_id) from countries where region_id >1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);

        //Assert that the number of region ids greater than 1 is 17.
        resultSet.next();
        int result = resultSet.getInt(1);

        assertEquals(17, result);

        //User closes the connection
        JdbcUtils.closeConnection();
    }

    @Test
    public void countryTest2() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDataBase();
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql = "SELECT region_id FROM countries WHERE region_id > 1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);
        List<Integer> region_idList = new ArrayList<>();

        while (resultSet.next()) {
            region_idList.add(resultSet.getInt("region_id"));
        }

        System.out.println("region_idList = " + region_idList);//[2, 3, 2, 2, 3, 4, 4, 3, 3, 4, 3, 2, 4, 3, 2, 4, 4]

        //Assert that the number of region ids greater than 1 is 17.
        assertEquals(17, region_idList.size());

        //User closes the connection
        JdbcUtils.closeConnection();

    }

}
