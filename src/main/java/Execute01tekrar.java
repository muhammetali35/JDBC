import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01tekrar {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","muhammet1");
        Statement statement = connection.createStatement();
        //1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.0
        boolean r1 = statement.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println("r1 = " + r1);//false

        //2.Örnek: Table'a worker_address sütunu ekleyin.
          boolean r2 = statement.execute("ALTER TABLE workers ADD worker_address VARCHAR(100)");
          System.out.println("r2 = " + r2);

        //3.Örnek: workers table'ını silin.
        boolean r3 = statement.execute("DROP TABLE workers ");
        System.out.println("r3 = " + r3);


        connection.close();
        statement.close();

    }
}
