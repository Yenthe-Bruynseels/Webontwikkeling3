package domain.db;

import domain.model.Contact;
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
        String sql = String.format("INSERT INTO %s.contact ( firstname, lastname, email, phonenumber, contactdate, user_id) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setString(4, contact.getPhonenumber());
            statementSQL.setTimestamp(5, contact.getTimestamp());
            statementSQL.setString(6, contact.getUserid());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact ORDER BY contactdate ", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                Timestamp date = result.getTimestamp("contactdate");
                int id = result.getInt("id");
                String userid = result.getString("user_id");
                Contact contact = new Contact(firstName, lastName, date, phonenumber, email, userid);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllContactsFromUntil(Timestamp from, Timestamp until) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE contactdate >= ? and contactdate < ? ORDER BY contactdate ", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, from);
            statementSql.setTimestamp(2, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                Timestamp date = result.getTimestamp("contactdate");
                int id = result.getInt("id");
                String userid = result.getString("user_id");
                Contact contact = new Contact(firstName, lastName, date, phonenumber, email, userid);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllContactsFromUser(String userid) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE user_id = ? ORDER BY contactdate", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                Timestamp date = result.getTimestamp("contactdate");
                int id = result.getInt("id");
                Contact contact = new Contact(firstName, lastName, date, phonenumber, email, userid);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllContactsFromUserFromUntil(String userid, Timestamp from, Timestamp until) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE user_id = ? and contactdate >= ? and contactdate < ? ORDER BY contactdate", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            statementSql.setTimestamp(2, from);
            statementSql.setTimestamp(3, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                String phonenumber = result.getString("phonenumber");
                Timestamp date = result.getTimestamp("contactdate");
                int id = result.getInt("id");
                Contact contact = new Contact(firstName, lastName, date, phonenumber, email, userid);
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
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    @Override
    public List<Contact> getAllContactsSinceLatestPositiveTest(String userid) {
        List<Contact> contacts = new ArrayList<Contact>();
        String sql = String.format("SELECT co.* FROM %s.user as u inner join %s.positivetest as pt on u.userid = pt.user_id inner join %s.contact as co on u.userid = co.user_id WHERE u.userid = ? and DATE(co.contactdate) >= pt.testdate and pt.testdate = (SELECT max(pt.testdate) FROM %s.user as u inner join %s.positivetest as pt on u.userid = pt.user_id WHERE u.userid = ?) ORDER BY contactdate", this.schema, this.schema, this.schema, this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            statementSql.setString(2, userid);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String phonenumber = result.getString("phonenumber");
                String email = result.getString("email");
                Timestamp date = result.getTimestamp("contactdate");

                Contact contact = new Contact(firstName, lastName, date, phonenumber, email, userid);
                contacts.add(contact);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

}
