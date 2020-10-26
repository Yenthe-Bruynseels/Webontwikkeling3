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
        String setSearchPath = "SET search_path = bruynseelsyenthe_web3;";
        Connection connection = DriverManager.getConnection(url,properties);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute(setSearchPath);
        statement.executeQuery( "SELECT * FROM person;" );
        connection.commit();
        ResultSet result = statement.getResultSet();
        while(result.next()){
            String userid = result.getString("userid").trim();
            // Waarom .trim() nodig? zonder valideert email niet. Denk mogelijks \t in string, aangezien andere velden strings trimmen, tabs weghaalt.
            String email = result.getString("email").trim();
            String password = result.getString("password").trim();
            String firstName = result.getString("first name").trim();
            String lastName = result.getString("last name").trim();
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