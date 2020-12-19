package domain.db;

import domain.model.Contact;
import java.sql.Timestamp;
import java.util.List;

public interface ContactDB {

    void add(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getAllContactsFromUntil(Timestamp from, Timestamp until);

    List<Contact> getAllContactsFromUser(String userid);

    List<Contact> getAllContactsFromUserFromUntil(String userid, Timestamp from, Timestamp until);

    void delete(int contactId);

    List<Contact> getAllContactsSinceLatestPositiveTest(String userid);
}