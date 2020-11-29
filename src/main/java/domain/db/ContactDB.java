package domain.db;

import domain.model.Contact;
import ui.controller.Contacts;

import java.util.List;

public interface ContactDB {

    void add(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getAllContactsFromUser(String userid);

    void delete(int contactId);

    List<Contact> getAllContactsSinceLatestPositiveTest(String userid);
}