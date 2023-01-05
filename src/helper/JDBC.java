package helper;


import java.sql.Connection;
import java.sql.DriverManager;
/**This class configures the JDBC driver and defines methods for making connections to database.*/
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    //private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String databaseName = "client_schedule";
    private static final String jdbUrl = protocol + vendor + location + databaseName + "?connectionTimezone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String adminUserName = "root";
    private static final String adminPassword = "Passw0rd!";
    public static Connection connection;
/** This method opens a connection with a database. */
    public static void openConnection()  {
        try { Class.forName(driver);
            connection = DriverManager.getConnection(jdbUrl,adminUserName,adminPassword);
    }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

}
/** This method closes the connection with a database.*/
    public static void closeConnection()   {
        try{
            connection.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
