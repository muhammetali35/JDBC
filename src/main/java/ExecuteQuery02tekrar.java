import java.sql.*;

public class ExecuteQuery02tekrar {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","muhammet1");
        Statement statement = connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        //1.Yol offset ve limit kullanarak
        System.out.println("1.Yol: ");
        String sql1= "select company,number_of_employees from companies order by number_of_employees desc offset 1 limit 1";
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+"--"+ resultSet.getObject(2));
        }

        //2. Yol: Sub Query kullanarak
        System.out.println("2.Yol: ");
        String sql2 = "SELECT company, number_of_employees  FROM companies \n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies \n" +
                "WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))";

        ResultSet resultSet2= statement.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getObject("company")+"--"+ resultSet2.getObject("number_of_employees"));
        }



        connection.close();
        statement.close();

    }
}
