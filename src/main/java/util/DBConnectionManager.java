package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    public Connection connection;
    private static DBConnectionManager dbConnectionManager_instance = null;

    private DBConnectionManager(String dbURL) {
        Properties dbProperties = new Properties();
        dbProperties.setProperty("user", "local_r0748609");
        dbProperties.setProperty("password", "ZnZX,9:$kw:Isu");
        dbProperties.setProperty("ssl", "true");
        dbProperties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        dbProperties.setProperty("sslmode", "prefer");

        try {
            System.out.print("connecting to database ...");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbURL, dbProperties);

            System.out.println("done");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException().getMessage());
        } catch (SQLException e) {
            System.out.print("connection troubles");
            System.out.println(e.getErrorCode());
        }
    }

    public static DBConnectionManager getInstance(String dbURL) {
        if (dbConnectionManager_instance == null) {
            dbConnectionManager_instance = new DBConnectionManager(dbURL);
        }
        return dbConnectionManager_instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

}