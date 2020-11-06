package domain.service;

import domain.db.ContactDB;
import domain.db.ContactDBSQL;
import domain.model.Contact;

import java.util.List;

public class ContactService {
    private ContactDB contactDB = new ContactDBSQL();

    public ContactService() {
    }

    public List<Contact> getContacts() {
        return getContactDb().getAll();
    }

    private ContactDB getContactDb() {
        return contactDB;
    }

    public void addContact(Contact contact) {
        getContactDb().add(contact);
    }

    public void deleteContact(int id) {
        getContactDb().delete(id);
    }
}
