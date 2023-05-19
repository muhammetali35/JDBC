import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","muhammet1");
        Statement statement = connection.createStatement();
        //1.Örnek: Selamlama yapan bir functıon'i Callable Statement ile giriniz

        //1.Adım Function'ı oluşturan kodu yazınız.
        String sql = "CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS $$ \n" +
                "BEGIN RETURN 'Merhaba '|| x ||', nasılsın?'; END; $$ LANGUAGE plpgsql;";

        //2.Adım: Function kodunu çalıştırınız.
        statement.execute(sql);

        //3.Adım: Function kodunu çalıştırınız.
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");

        //4.Adım: Return için registerOutParameter() methodunu, parametreler için ise setString, setInt gibi
        //        methodları kullanırız.
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.setString(2,"Ali");

        //5. Adım: execute() methodu ile callableStatement'ı çalıştırınız
        callableStatement.execute();

        //6. Adım: Sonucu okumak için callableStatement'tan data türünü çağır
        //callableStatement'ta dönen data resultset içinde dönmez.Direkt callableStatement içerinden alınır.
        System.out.println(callableStatement.getObject(1));

        //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.


         //1.Adım Function'ı oluşturan kodu yazınız.
            String sql2 = "CREATE OR REPLACE FUNCTION toplama(x INT , y INT) RETURNS NUMERIC \n" +
                    "AS $$ BEGIN RETURN x + y ; END; $$ LANGUAGE plpgsql;";
        //2.Adım: Function kodunu çalıştırınız.
        statement.execute(sql2);

        //3.Adım: Function kodunu çalıştırınız.
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?,?)}");

        //4.Adım: Return için registerOutParameter() methodunu, parametreler için ise setString, setInt gibi
        //        methodları kullanırız.
        callableStatement2.registerOutParameter(1,Types.NUMERIC);
        callableStatement2.setInt(2,-5);
        callableStatement2.setInt(3,15);

        //5. Adım: execute() methodu ile callableStatement'ı çalıştırınız
        callableStatement2.execute();

        //6. Adım: Sonucu okumak için callableStatement'tan data türünü çağır
        //callableStatement'ta dönen data resultset içinde dönmez.Direkt callableStatement içerinden alınır.
        System.out.println("callableStatement2.getObject(1) = " + callableStatement2.getObject(1));

        connection.close();
        statement.close();
    }
}
