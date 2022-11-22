package helper;


import java.sql.Connection;
import java.sql.DriverManager;

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

    public static void openConnection()  {
        try { Class.forName(driver);
            connection = DriverManager.getConnection(jdbUrl,adminUserName,adminPassword);
            System.out.println("connection successful");
    }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

}

    public static void closeConnection()   {
        try{
            connection.close();
            System.out.println("disconnected successfuly");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
