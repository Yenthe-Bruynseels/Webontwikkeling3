package ui.view;

import domain.model.Person;
import domain.model.Role;
import util.Secret;

import java.sql.*;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:62021/2TX38";
        try {
            Class.forName("util.Secret"); // implementation of abstract class Credentials
            Secret.setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class util.Secret with credentials not found!");
        }
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
            Role role = Role.valueOf(result.getString("role").toUpperCase());
            try { // validation of data stored in database
                Person person = new Person(userid, email, password, firstName, lastName);
                person.setRole(role);
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