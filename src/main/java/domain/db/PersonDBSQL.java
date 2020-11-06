package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.user (userid, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getFirstName());
            statementSQL.setString(3, person.getLastName());
            statementSQL.setString(4, person.getEmail());
            statementSQL.setString(5, person.getPassword());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> people = new ArrayList<Person>();
        String sql = String.format("SELECT * FROM %s.user", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                Person person = new Person(userid, email, password, firstname, lastname);
                people.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return people;
    }

    @Override
    public Person get(String personId) {
        if (personId == null) throw new DbException("No id given");
        Person person = null;
        String sql = String.format("SELECT * FROM %s.user WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                person = new Person(userid, email, password, firstname, lastname);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return person;
    }
    @Override
    public void delete(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }

        String sql = String.format("DELETE FROM %s.user WHERE userid = ?;", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            statementSql.execute();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    @Override
    public Person getPersonIfAuthenticated(String personId, String password) {
        Person current = get(personId);
        if (current != null && current.isCorrectPassword(password)) {
            return current;
        }
        return null;
    }
}
