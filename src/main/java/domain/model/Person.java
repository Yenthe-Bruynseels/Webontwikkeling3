package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public Person(String userid, String email, String password, String firstName, String lastName) {
        setUserid(userid);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        userid = userid.trim();
        if (userid.trim().isEmpty()) {
            throw new DomainException("No username given");
        }
        this.userid = userid.toLowerCase();
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


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        password = password.trim();
        if (password.isEmpty()) {
            throw new DomainException("No password given");
        }
        return getPassword().equals(hashPassword(password));
    }

    public void setPassword(String password) {
        password = password.trim();
        if (password.isEmpty()) {
            throw new DomainException("No password given");
        }
        this.password = password;
    }

    public void setPasswordHashed(String password) {
        password = password.trim();
        if (password.isEmpty()) {
            throw new DomainException("No password given");
        }
        this.password = hashPassword(password);
    }


    private String hashPassword(String password) {
        try {
//create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
//reset
            crypt.reset();
//update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
//digest
            byte[] digest = crypt.digest();
//convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
//return hashed password
            return digestAsBigInteger.toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DomainException(e.getMessage(), e);
        }
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

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }
}
