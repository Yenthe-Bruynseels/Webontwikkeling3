package domain.db;

import domain.model.Contact;

import java.util.List;

public interface ContactDB {

    void add(Contact contact);

    List<Contact> getAll();

    void delete(int contactId);
}