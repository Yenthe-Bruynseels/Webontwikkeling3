package domain.service;

import domain.db.ContactDB;
import domain.db.ContactDBSQL;
import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Contact;
import domain.model.Person;

import java.util.List;

public class ContactTracingService {
    private PersonDB personDb = new PersonDBSQL();

    public ContactTracingService() {
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

/*    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }*/

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonDB getPersonDb() {
        return personDb;
}

    public Person authenticate(String userid, String password) {
        return getPersonDb().getPersonIfAuthenticated(userid, password);
    }


//-----------------------------------------------------------------------------------------------------------------------------//

    private ContactDB contactDB = new ContactDBSQL();

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
