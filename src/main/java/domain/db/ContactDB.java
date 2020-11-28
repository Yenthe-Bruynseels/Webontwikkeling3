package domain.db;

import domain.model.Contact;

import java.util.List;

public interface ContactDB {

    void add(Contact contact);

    List<Contact> getAllAdmin();

    List<Contact> getAllContactsFromUser(String userid);

    void delete(int contactId);
}