import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {


final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
final static String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
final static String JDBC_CONNECTION_USER = "root";
final static String JDBC_CONNECTION_PASS = "Android75";
final static String TOKEN_KEY = "Acc3s0@Dato$";


public static Connection createConnection() throws Exception {

Class.forName(JDBC_DRIVER);

return DriverManager.getConnection(JDBC_CONNECTION_STRING, JDBC_CONNECTION_USER, JDBC_CONNECTION_PASS);

}


}