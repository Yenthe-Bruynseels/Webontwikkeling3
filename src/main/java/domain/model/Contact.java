package domain.model;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private Timestamp timestamp;
    private String phonenumber;
    private String email;
    private int id;

    public Contact(String firstName, String lastName, Timestamp timestamp, String phonenumber, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setTimestamp(timestamp);
        setPhonenumber(phonenumber);
        setEmail(email);
    }

    public Contact() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName.trim();
        if (firstName.isEmpty()) {
            throw new DomainException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName.trim();
        if (lastName.isEmpty()) {
            throw new DomainException("No last name given");
        }
        this.lastName = lastName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        if (timestamp == null) throw new DomainException("Date cn't be null");
        this.timestamp = timestamp;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        phonenumber = phonenumber.trim();
        if (phonenumber.isEmpty()) throw new DomainException("Phone number can't be empty.");
        if (!phonenumber.matches("[+]324\\d{8}")) throw new DomainException("Invalid phone number");
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.trim();
        if (email.isEmpty()) {
            throw new DomainException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new DomainException("Email not valid");
        }
        this.email = email;
    }
}
