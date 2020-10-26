package ui.view;

import domain.model.Person;

import java.sql.*;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:62021/2TX38";
        properties.setProperty("user", "local_r0748609");
        properties.setProperty("password", "ZnZX,9:$kw:Isu");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");
        properties.setProperty("allowMultiQueries","true");
        properties.setProperty("prepareThreshold","0");
        String setSearchPath = "SET search_path = web3_project_r0748609;";
        Connection connection = DriverManager.getConnection(url,properties);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute(setSearchPath);
        statement.executeQuery( "SELECT * FROM web3_project_r0748609.user;" );
        connection.commit();
        ResultSet result = statement.getResultSet();
        while(result.next()){
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("firstname");
            String lastName = result.getString("lastname");
            try { // validation of data stored in database
                Person person = new Person(userid, email, password, firstName, lastName);
                System.out.println(person.toString());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement.close();
        connection.close();
    }
}