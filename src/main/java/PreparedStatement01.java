import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","muhammet1");
        Statement statement = connection.createStatement();
   //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //Prepared statement oluşturmak için :
        //1.Adım: Prepareed query'si oluştur -->Parametrelendirme yapılacak yerlere ? gir
        String sql = "UPDATE companies SET number_of_employees = ? WHERE company= ?";//? --> Parametrelendirme

        //2.Adım: Prepared statement oluştur
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3.Adım: PreparedStatement objesi ile setInt gibi methodlarla Parametrelerin yerine koymak istediğimiz değerleri yerleştir
        preparedStatement.setInt(1,9999 );
        preparedStatement.setString(2,"IBM" );


        //4.Adım Query çalıştırır
        int guncellenenSayisi =preparedStatement.executeUpdate();

        //Guncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:
        String sql2="SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()){//ResultSet son satıra gelip "false" döndükten sonra kapatılır. Kapalı ResultSet üzerinde işlem yapılamaz.
            System.out.println(resultSet.getObject("company_id")+"--"+resultSet.getObject("company")+"-- "+resultSet.getObject("number_of_employees"));
            //System.out.println(resultSet.getObject(1)+"--"+resultSet.getObject(2)+"-- "+resultSet.getObject("3"));

        }


        connection.close();
        statement.close();
    }
}
