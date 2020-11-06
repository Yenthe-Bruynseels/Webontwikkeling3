package domain.db;

import domain.model.Contact;
import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.contact ( firstname, lastname, email, phonenumber, date) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setString(4, contact.getPhonenumber());
            statementSQL.setTimestamp(5, contact.getTimestamp());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<Contact>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                Timestamp date = result.getTimestamp("date");
                int id = result.getInt("id");
                Contact contact = new Contact(firstName, lastName, date, phonenumber, email);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public void delete(int contactID) {
        String sql = String.format("DELETE FROM %s.contact WHERE id = ?;", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setInt(1, contactID);
            statementSql.execute();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }

    }
}
